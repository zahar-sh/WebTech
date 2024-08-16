package com.example.fundamentals;

import java.util.Arrays;
import java.util.Objects;

public class Prime {
    private static final int[] EMPTY_ARRAY = {};

    private final int[] numbers;
    private final int from, to;

    public Prime(int[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public Prime(int[] numbers, int from, int to) {
        Objects.requireNonNull(numbers);
        Objects.checkFromToIndex(from, to, numbers.length);

        this.numbers = numbers;
        this.from = from;
        this.to = to;
    }

    public int[] primeNumberIndexes() {
        final int len = to - from;
        if (len == 0)
            return EMPTY_ARRAY;

        int size = 0;
        int[] indexes = new int[len];

        for (int i = from; i < to; i++) {
            if (isPrime(numbers[i])) {
                indexes[size++] = i;
            }
        }
        if (size == 0)
            return EMPTY_ARRAY;
        if (size == len)
            return indexes;
        return Arrays.copyOf(indexes, size);
    }

    public static boolean isPrime(long number) {
        if (number < 2)
            return false;
        if (number == 2)
            return true;
        if ((number & 1) == 0)
            return false;
        for (long i = 3; i * i <= number; i += 2)
            if (number % i == 0)
                return false;
        return true;
    }
}