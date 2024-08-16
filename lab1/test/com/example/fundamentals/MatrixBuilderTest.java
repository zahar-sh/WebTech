package com.example.fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatrixBuilderTest {

    @Test
    void buildMatrix() {
        double[] src = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        double[][] expected = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };


        double[][] actual = new MatrixBuilder(src).build();

        Assertions.assertEquals(expected.length, actual.length);

        for (int y = 0; y < actual.length; y++) {
            Assertions.assertArrayEquals(expected[y], actual[y]);
        }
    }
}