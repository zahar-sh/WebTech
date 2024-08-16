package com.example.fundamentals;

import com.example.fundamentals.utils.Input;

import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class MathFunApp {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String cancel = "cancel";
            double x = Input.readDouble(scanner, "Enter x", cancel);
            double y = Input.readDouble(scanner, "Enter y", cancel);
            MathFun fun = new MathFun();
            System.out.println("F(x, y)=" + fun.calc(x, y));
        } catch (CancellationException ignored) {
        }
    }
}
