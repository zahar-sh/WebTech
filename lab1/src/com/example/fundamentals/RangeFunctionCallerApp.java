package com.example.fundamentals;

import com.example.fundamentals.utils.Input;

import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class RangeFunctionCallerApp {

    public static void main(String[] args) {
        try {
            String cancel = "cancel";
            Scanner scanner = new Scanner(System.in);

            double a = Input.readDouble(scanner, "Enter a:", cancel);
            double b = Input.readDouble(scanner, "Enter b:", cancel);
            double h = Input.readDouble(scanner, "Enter h:", cancel);

            RangeFunctionCaller functionCaller = new RangeFunctionCaller(Math::tan, a, b, h);
            functionCaller.callFunctionOnRange((x, y) -> {
                System.out.printf("x = %.5f, F(x) = %.5f \n", x, y);
            });
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage() == null ? "Error" : e.getMessage());
        } catch (CancellationException ignored) {
        }
    }
}
