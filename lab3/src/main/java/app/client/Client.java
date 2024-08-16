package app.client;

import app.client.utils.ArgsParser;
import app.dto.DocumentDto;
import app.dto.UserDto;
import app.error.ErrorResponse;
import app.enums.MessageType;
import app.connection.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Client implements Runnable {
    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    private final String host;
    private final int port;
    private final Scanner scanner;
    private final PrintWriter writer;
    private UserDto me = null;

    public Client() {
        Scanner scanner = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out, true);
        writer.println("Enter host");
        this.host = scanner.nextLine();
        writer.println("Enter port");
        this.port = scanner.nextInt();
        this.scanner = scanner;
        this.writer = writer;
    }

    @Override
    public void run() {
        Thread printerThread = null;
        try (Socket socket = new Socket(host, port);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            printerThread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Message response = (Message) in.readObject();
                        printResponse(response);
                    } catch (IOException e) {
                        break;
                    } catch (Exception e) {
                        handleException(e);
                    }
                }
            });
            printerThread.start();

            printResponse(buildRequest(MessageType.HELP.name()));
            writer.println("Enter commands");
            while (true) {
                try {
                    String line = scanner.nextLine();
                    Message msg = buildRequest(line);
                    if (msg.getType() == MessageType.EXIT)
                        break;
                    out.reset();
                    out.writeObject(msg);
                    out.flush();
                } catch (SocketException e) {
                    break;
                } catch (Exception e) {
                    handleException(e);
                }
            }
        } catch (IOException e) {
            handleException(e);
        } finally {
            if (printerThread != null)
                printerThread.interrupt();
        }
    }

    private String readStringFromPath(String[] args) {
        return ArgsParser.getValue(args, 1, args.length, "-path", true, s -> {
            try {
                return Files.readString(Paths.get(s));
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        });
    }

    public <T extends Number> T getId(String[] args, boolean required, Function<String, T> mapper) {
        return ArgsParser.getNumber(args, 1, args.length, "-id", required, mapper);
    }

    private Message buildRequest(String line) {
        String[] args = line.split("\\s+");
        String command = args[0];
        MessageType type = MessageType.valueOf(command.toUpperCase(Locale.ROOT));
        switch (type) {
            case HELP: {
                String help = Arrays.stream(MessageType.values())
                        .map(Enum::name)
                        .map(String::toLowerCase)
                        .collect(Collectors.joining("\n"));
                return new Message(type, help);
            }
            case REGISTER:
            case AUTH: {
                String email = ArgsParser.getValue(args, 1, args.length, "-email");
                String password = ArgsParser.getValue(args, 1, args.length, "-password");
                UserDto dto = new UserDto(null, email, password, null, null);
                return new Message(type, dto);
            }
            case USER_FIND_BY_ID:
            case USER_DELETE_BY_ID: {
                Integer userId = getId(args, true, Integer::parseInt);
                return new Message(type, me, userId);
            }
            case USER_FIND_ALL:
            case USER_DELETE_ALL:
            case DOCUMENT_FIND_ALL:
            case DOCUMENT_DELETE_ALL:
                return new Message(type, me);
            case DOCUMENT_SAVE: {
                String text = readStringFromPath(args);
                Long documentId = getId(args, false, Long::parseLong);
                DocumentDto target = new DocumentDto(documentId, text);
                return new Message(type, me, target);
            }
            case DOCUMENT_FIND_BY_ID:
            case DOCUMENT_DELETE_BY_ID: {
                Long documentId = getId(args, true, Long::parseLong);
                return new Message(type, me, documentId);
            }
        }
        return new Message(type);
    }

    private void printResponse(Message request) {
        Object[] args = request.getArgs();

        writer.println(request.getType().name());
        switch (request.getType()) {
            case HELP:
                writer.println(args[0]);
                break;
            case REGISTER:
            case AUTH:
                me = (UserDto) args[0];
                break;
            case USER_FIND_BY_ID:
                UserDto userDto = (UserDto) args[0];
                writer.println(userDto);
                break;
            case USER_FIND_ALL:
            case DOCUMENT_FIND_ALL:
                writer.println(Arrays.stream(args).map(String::valueOf).collect(Collectors.joining("\n")));
                break;
            case DOCUMENT_FIND_BY_ID:
            case DOCUMENT_SAVE:
                DocumentDto documentDto = (DocumentDto) args[0];
                writer.println(documentDto);
                break;
            case ERROR:
                ErrorResponse response = (ErrorResponse) args[0];
                writer.println(response);
                break;
        }
    }

    protected void handleException(Exception e) {
        writer.printf("%s-%s\n", e.getClass().getSimpleName(), e.getMessage());
    }
}

