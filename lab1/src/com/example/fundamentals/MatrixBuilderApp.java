package com.example.fundamentals;

import com.example.fundamentals.utils.Input;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class MatrixBuilderApp {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            double[] array = Input.readArray(scanner);
            System.out.println("Your array:");
            System.out.println(Arrays.toString(array));

            MatrixBuilder builder = new MatrixBuilder(array);
            double[][] matrix = builder.build();
            System.out.println("Matrix:");
            for (double[] row : matrix)
                System.out.println(Arrays.toString(row));
        } catch (CancellationException ignored) {
        }
    }
}
