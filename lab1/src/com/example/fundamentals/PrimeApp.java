package com.example.fundamentals;

import com.example.fundamentals.utils.Input;

import java.util.Arrays;
import java.util.Scanner;

public class PrimeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(Input.readArray(scanner)).
                mapToInt(value -> (int) value).toArray();

        System.out.println("Your array:");
        System.out.println(Arrays.toString(array));

        Prime prime = new Prime(array);
        int[] primeNumberIndexes = prime.primeNumberIndexes();

        if (primeNumberIndexes.length == 0) {
            System.out.println("Prime number not found");
        } else {
            System.out.println("Prime number numbers:");
            int[] primeNumberNumbers = Arrays.stream(primeNumberIndexes).map(i -> i + 1).toArray();
            System.out.println(Arrays.toString(primeNumberNumbers));
        }
    }
}
