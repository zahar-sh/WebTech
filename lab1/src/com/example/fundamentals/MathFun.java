package com.example.fundamentals;

import com.example.fundamentals.utils.Numbers;

public class MathFun {
    public double calc(double x, double y) {
        Numbers.requireNonNaN(x);
        Numbers.requireNonNaN(y);

        double sin = Math.sin(x + y);
        double a = 1 + sin * sin;

        double div = 1 + (x * x) * (y * y);
        double toAbs = x - (2 * x) / div;
        double b = 2 + Math.abs(toAbs);

        return (a / b) + x;
    }
}