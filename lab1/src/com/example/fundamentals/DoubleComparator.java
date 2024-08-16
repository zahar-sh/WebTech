package com.example.fundamentals;

public interface DoubleComparator {
    DoubleComparator NORMAL = Double::compare;
    DoubleComparator REVERSE = (v1, v2) -> Double.compare(v2, v1);

    int compare(double v1, double v2);
}