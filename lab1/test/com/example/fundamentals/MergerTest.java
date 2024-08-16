package com.example.fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MergerTest {

    @Test
    void merge() {
        double[] a = {1, 3, 5, 5, 7, 8, 9, 9};
        double[] b = {1, 2, 3, 3, 5, 6, 7, 8, 8};

        double[] expected = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, expected, a.length, b.length);
        Arrays.sort(expected);

        double[] actual = new Merger(a, 0, a.length, b, 0, b.length, DoubleComparator.NORMAL).merge();

        Assertions.assertArrayEquals(expected, actual);
    }
}