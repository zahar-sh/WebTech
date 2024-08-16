package com.example.fundamentals.utils;

import com.example.fundamentals.DoubleComparator;

import java.util.Objects;

public class Numbers {
    public static void requireNonNaN(double val) {
        if (Double.isNaN(val))
            throw new IllegalArgumentException("Not a number");
    }

    public static void requireMinMax(double min, double max) {
        if (min > max)
            throw new IllegalArgumentException("Min value is greater then Max value");
    }

    public static void requireNonNegative(double value) {
        if (value < 0)
            throw new IllegalArgumentException("Value should be positive");
    }


    public static int binarySearch(double key, double[] array,
                                   int low, int high,
                                   DoubleComparator cmp) {
        Objects.requireNonNull(array);
        Objects.checkFromToIndex(low, high, array.length);
        Objects.requireNonNull(cmp);

        while (low < high) {
            int mid = (low + high - 1) >>> 1;
            int c = cmp.compare(array[mid], key);
            if (c < 0)
                low = mid + 1;
            else if (c > 0)
                high = mid;
            else
                return mid;
        }
        return -(low + 1);
    }
}