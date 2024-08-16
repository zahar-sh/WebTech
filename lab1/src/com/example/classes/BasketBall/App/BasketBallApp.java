package com.example.classes.BasketBall.App;

import com.example.classes.BasketBall.Lib.Basket;
import com.example.classes.BasketBall.Lib.Color;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BasketBallApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PrintStream out = System.out;
        PrintStream err = System.err;

        String help = Arrays.stream(Command.values()).map(Command::infoString).
                collect(Collectors.joining("\n", "", "\nColors:" +
                        Arrays.toString(Color.values())));
        String cancel = Command.EXIT.getKey();

        Basket basket = new Basket();
        Runnable task = new Task(input, out, err, basket, help, cancel);
        task.run();
    }
}

