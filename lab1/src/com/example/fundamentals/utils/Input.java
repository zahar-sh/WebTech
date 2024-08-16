package com.example.fundamentals.utils;

import java.util.*;
import java.util.concurrent.CancellationException;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Input {
    public static double readDouble(Scanner scanner, String msg, String cancel) throws CancellationException {
        System.out.println(msg);
        return readDouble(scanner, cancel);
    }

    public static double readDouble(Scanner scanner, String cancel) throws CancellationException {
        Consumer<String> onInputMismatch = cancel == null ? (s) -> {
            System.err.println("This is not number");
        } : (s) -> {
            if (cancel.equals(s))
                throw new CancellationException();
            System.err.println("This is not number, enter: \"" + cancel + "\", to cancel");
        };

        while (true) {
            String s = scanner.next();
            try {
                double value = Double.parseDouble(s);
                scanner.nextLine();
                return value;
            } catch (NumberFormatException e) {
                onInputMismatch.accept(s);
            }
        }
    }

    public static DoubleStream toDouble(Stream<String> stream) {
        Objects.requireNonNull(stream);
        return stream.filter(s -> {
            try {
                Double.parseDouble(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }).mapToDouble(Double::parseDouble);
    }

    public static DoubleStream doubleStreamOf(Scanner scanner, String stop) {
        Objects.requireNonNull(scanner);
        Objects.requireNonNull(stop);

        Iterator<String> iterator = new Iterator<>() {
            @Override
            public boolean hasNext() {
                return scanner.hasNext() &&
                        !scanner.hasNext(stop);
            }

            @Override
            public String next() {
                if (hasNext())
                    return scanner.next();
                throw new NoSuchElementException();
            }
        };
        return toDouble(StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, 0), false)).
                onClose(() -> scanner.next(stop));
    }

    public static DoubleStream doubleStreamOf(Scanner scanner, int len) {
        Objects.requireNonNull(scanner);
        Numbers.requireNonNegative(len);

        if (len == 0)
            return DoubleStream.empty();

        Iterator<String> iterator = new Iterator<>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < len && scanner.hasNext();
            }

            @Override
            public String next() {
                if (hasNext()) {
                    i++;
                    return scanner.next();
                }
                throw new NoSuchElementException();
            }
        };
        return toDouble(StreamSupport.stream(
                Spliterators.spliterator(iterator, len, Spliterator.SIZED), false));
    }

    public static double[] readArray(Scanner scanner) {
        System.out.println("Enter input method:\n1 - fixed size\n2 - \"stop\" word");
        String token = scanner.nextLine();

        DoubleStream numbers;
        switch (token) {
            case "1":
                int size = (int) Input.readDouble(scanner, "Enter size", "cancel");
                System.out.println("Enter numbers:");
                numbers = Input.doubleStreamOf(scanner, size);
                break;
            case "2":
                System.out.println("Enter \"stop\" word:");
                String stop = scanner.nextLine();
                System.out.println("Enter numbers:");
                numbers = Input.doubleStreamOf(scanner, stop);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + token);
        }
        try {
            return numbers.toArray();
        } finally {
            numbers.close();
            scanner.nextLine(); //ship \n in buffer
        }
    }
}
