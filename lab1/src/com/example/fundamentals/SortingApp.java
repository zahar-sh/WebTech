package com.example.fundamentals;

import com.example.fundamentals.utils.Input;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class SortingApp {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            double[] array = Input.readArray(scanner);
            System.out.println("Your array:");
            System.out.println(Arrays.toString(array));

            Sorting.sort(array, DoubleComparator.NORMAL);

            System.out.println("Sorted array:");
            System.out.println(Arrays.toString(array));
        } catch (CancellationException ignored) {
        }
    }
}
