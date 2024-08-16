package com.example.fundamentals;

import java.util.Objects;

public class MatrixBuilder {
    private final double[] src;
    private final int from, to;

    public MatrixBuilder(double[] src) {
        this(src, 0, src.length);
    }
    public MatrixBuilder(double[] src, int from, int to) {
        Objects.requireNonNull(src);
        Objects.checkFromToIndex(from, to, src.length);
        this.src = src;
        this.from = from;
        this.to = to;
    }

    public double[][] build() {
        int len = to - from;

        if (len == 0)
            return new double[0][];

        double[][] matrix = new double[len][len];
        for (int y = 0; y < len; y++) {
            for (int x = 0; x < len; x++) {
                matrix[y][x] = src[((y + x) % len) + from];
            }
        }
        return matrix;
    }
}
