package com.example.fundamentals;

import com.example.fundamentals.utils.Input;

import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class FigureApp {
    public static void main(String[] args) {
        final String cancel = "cancel";
        String help =
                "                     |y\n" +
                        "                     |\n" +
                        "                     | yMax\n" +
                        "               ______|______\n" +
                        "               |     |     | x1\n" +
                        "               |     |     |\n" +
                        "               |     |     |              x\n" +
                        "_______________|_____|_____|_________________\n" +
                        "          |          |  x, y    |\n" +
                        "          |          |   .      | x2\n" +
                        "          |__________|__________|\n" +
                        "                     |  yMin\n" +
                        "                     |\n" +
                        "                     |";

        System.out.println(help);
        Scanner scanner = new Scanner(System.in);
        try {
            double yMin = Input.readDouble(scanner, "Enter yMin: ", cancel);
            double yMax = Input.readDouble(scanner, "Enter yMax: ", cancel);

            double x1 = Input.readDouble(scanner, "Enter x1: ", cancel);
            double x2 = Input.readDouble(scanner, "Enter x2: ", cancel);

            Figure figure = new Figure(yMin, yMax, x1, x2);
            while (true) {
                System.out.println("Do you want to check the point? [Yes=yes,No=other]");
                String token = scanner.nextLine();
                if (!"yes".equals(token))
                    break;
                double x = Input.readDouble(scanner, "Enter x: ", cancel);
                double y = Input.readDouble(scanner, "Enter y: ", cancel);

                boolean contains = figure.contains(x, y);
                System.out.println("Contains? " + contains);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Figure creation error");
        } catch (CancellationException ignored) {
        }
    }
}
