package com.example.fundamentals;

import org.junit.jupiter.api.Assertions;

class FigureTest {

    @org.junit.jupiter.api.Test
    void contains() {
        Figure f1 = new Figure(-3, 0, 5,
                -4, 4,
                -6, 6);
        Assertions.assertTrue(f1.contains(0, 0));

        Figure f2 = new Figure(-3, 0, 5,
                -4, 4,
                -6, 6);
        Assertions.assertFalse(f2.contains(123, 0));
    }

    @org.junit.jupiter.api.Test
    void testContains() {
        Assertions.assertTrue(
                Figure.contains(-6, 9, 6));
        Assertions.assertFalse(
                Figure.contains(-6, 9, 10));
    }
}