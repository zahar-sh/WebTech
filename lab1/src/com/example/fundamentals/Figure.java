package com.example.fundamentals;

import com.example.fundamentals.utils.Numbers;

public class Figure {
    private final double yMin;
    private final double yDelimiter;
    private final double yMax;

    private final double xMin1;
    private final double xMax1;

    private final double xMin2;
    private final double xMax2;

    public Figure(double yMin, double yMax, double x1, double x2) {
        this(yMin, 0, yMax, -x1, x1, -x2, x2);
    }
    public Figure(double yMin, double yDelimiter, double yMax, double xMin1, double xMax1, double xMin2, double xMax2) {
        Numbers.requireNonNaN(yMin);
        Numbers.requireNonNaN(yDelimiter);
        Numbers.requireNonNaN(yMax);

        Numbers.requireNonNaN(xMin1);
        Numbers.requireNonNaN(xMax1);
        Numbers.requireNonNaN(xMin2);
        Numbers.requireNonNaN(xMax2);

        Numbers.requireMinMax(yMin, yDelimiter);
        Numbers.requireMinMax(yDelimiter, yMax);

        Numbers.requireMinMax(xMin1, xMax1);
        Numbers.requireMinMax(xMin2, xMax2);
        this.yMin = yMin;
        this.yDelimiter = yDelimiter;
        this.yMax = yMax;
        this.xMin1 = xMin1;
        this.xMax1 = xMax1;
        this.xMin2 = xMin2;
        this.xMax2 = xMax2;
    }

    public boolean contains(double x, double y) {
        Numbers.requireNonNaN(x);
        Numbers.requireNonNaN(y);

        if (y > yDelimiter) {
            return (y <= yMax) && contains(xMin1, xMax1, x);
        } else {
            return (y >= yMin) && contains(xMin2, xMax2, x);
        }
    }

    public static boolean contains(double min, double max, double x) {
        return x >= min && x <= max;
    }
}
