package com.example.fundamentals;

import com.example.fundamentals.utils.Input;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class SequenceApp {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            double[] doubles = Input.readArray(scanner);
            int[] array = Arrays.stream(doubles).mapToInt(i -> (int) i).toArray();

            Sequence sequence = new Sequence();
            System.out.println(sequence.amountToLis(array));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage() == null ? "Error" : e.getMessage());
        } catch (CancellationException ignored) {
        }
    }
}
