package com.example.fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SortingTest {

    @Test
    void sort() {
        double[] array = new double[30];
        for (int i = 0; i < array.length; i++)
            array[i] = Math.random() * 300;
        double[] expected = Arrays.copyOf(array, array.length);
        Arrays.sort(expected);

        Sorting.sort(array, DoubleComparator.NORMAL);
        Assertions.assertArrayEquals(expected, array);
    }
}