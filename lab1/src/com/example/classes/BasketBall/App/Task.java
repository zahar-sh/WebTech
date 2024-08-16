package com.example.classes.BasketBall.App;

import com.example.classes.BasketBall.Lib.Ball;
import com.example.classes.BasketBall.Lib.Basket;
import com.example.classes.BasketBall.Lib.Color;
import com.example.fundamentals.utils.Input;

import java.io.PrintStream;
import java.util.*;

class Task implements Runnable {
    private final Scanner scanner;
    private final PrintStream out;
    private final PrintStream err;
    private final Basket basket;

    private final String help;
    private final String cancel;
    private volatile boolean running = false;

    Task(Scanner input, PrintStream output, PrintStream err, Basket basket, String help, String cancel) {
        this.scanner = Objects.requireNonNull(input);
        this.out = Objects.requireNonNull(output);
        this.err = err == null ? output : err;
        this.basket = Objects.requireNonNull(basket);
        this.help = Objects.requireNonNull(help);
        this.cancel = cancel;
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        try {
            running = true;
            out.println(help);
            do {
                try {
                    out.println("Enter command");
                    String token = scanner.nextLine();
                    Optional<Command> optionalCommand = Arrays.stream(Command.values()).
                            filter(c -> c.getKey().equals(token)).
                            findFirst();
                    if (optionalCommand.isEmpty()) {
                        err.println("Invalid command");
                    } else {
                        handleCommand(optionalCommand.get());
                    }
                } catch (IllegalArgumentException e) {
                    err.println(e.getMessage() == null ? "Invalid input" : e.getMessage());
                }
            } while (isRunning());
        } finally {
            running = false;
        }
    }

    private void handleCommand(Command command) {
        switch (command) {
            case HELP:
                out.println(help);
                break;
            case ADD:
                try {
                    out.println("Enter color");
                    String name = scanner.nextLine();
                    Color color = Color.valueOf(name.toUpperCase(Locale.ROOT));
                    int weight = (int) Input.readDouble(scanner, "Enter weight:", cancel);
                    Ball ball = new Ball(color, weight);
                    basket.add(ball);
                } catch (IllegalArgumentException e) {
                    err.println("Choose one of the colors from the list:");
                    err.println(Arrays.toString(Color.values()));
                }
                break;
            case REMOVE:
                if (basket.isEmpty()) {
                    out.println("Basket is empty");
                    break;
                }
                int index = (int) Input.readDouble(scanner, "Enter index from 1 to " + basket.size() + ":", cancel);
                try {
                    basket.remove(index - 1);
                } catch (IndexOutOfBoundsException e) {
                    err.println(e.getMessage() == null ? "Invalid index" : e.getMessage());
                }
                break;
            case CLEAR:
                basket.clear();
                break;
            case PRINT:
                out.println("Basket:");
                basket.forEach(out::println);
                out.println();
                break;
            case TOTAL_WEIGHT:
                out.println(basket.totalWeight());
                break;
            case BLUE_BALL_COUNT:
                out.println(basket.countByColor(Color.BLUE));
                break;
            case EXIT:
                running = false;
                break;
        }
    }
}
