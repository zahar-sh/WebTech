package com.example.fundamentals;

import com.example.fundamentals.utils.Numbers;

import java.util.Objects;
import java.util.function.DoubleUnaryOperator;

public class RangeFunctionCaller {
    private final DoubleUnaryOperator function;
    private final double a, b, h;

    public RangeFunctionCaller(DoubleUnaryOperator function, double a, double b, double h) {
        Objects.requireNonNull(function);

        Numbers.requireNonNaN(a);
        Numbers.requireNonNaN(b);
        Numbers.requireNonNaN(h);

        Numbers.requireMinMax(a, b);

        Numbers.requireNonNegative(h);

        this.function = function;
        this.a = a;
        this.b = b;
        this.h = h;
    }

    public void callFunctionOnRange(DoubleBiConsumer callback) {
        Objects.requireNonNull(callback);
        for (double x = a; x < b; x += h) {
            callback.accept(x, function.applyAsDouble(x));
        }
    }
}
