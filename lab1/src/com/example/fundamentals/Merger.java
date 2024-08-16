package com.example.fundamentals;

import com.example.fundamentals.utils.Numbers;

import java.util.Objects;

public class Merger {
    private final double[] a;
    private final int from1, to1;
    private final double[] b;
    private final int from2, to2;
    private final DoubleComparator comparator;

    public Merger(double[] a, int from1, int to1,
                  double[] b, int from2, int to2,
                  DoubleComparator comparator) {
        Objects.requireNonNull(a);
        Objects.checkFromToIndex(from1, to1, a.length);

        Objects.requireNonNull(b);
        Objects.checkFromToIndex(from2, to2, b.length);
        this.a = a;
        this.from1 = from1;
        this.to1 = to1;
        this.b = b;
        this.from2 = from2;
        this.to2 = to2;
        this.comparator = comparator;
    }

    public double[] merge() {
        int len = (to1 - from1) + (to2 - from2);

        if (len == 0)
            return new double[0];

        int resultSize = to1 - from1;
        double[] array = new double[len];
        System.arraycopy(a, from1, array, 0, resultSize);

        for (int i = from2; i < to2; i++) {
            double element = b[i];

            int destPos = Numbers.binarySearch(element, array, 0, resultSize, comparator);
            if (destPos < 0)
                destPos = (-destPos) - 1;

            System.arraycopy(array, destPos, array, destPos + 1, resultSize - destPos);
            array[destPos] = element;
            resultSize++;
        }
        return array;
    }
}
