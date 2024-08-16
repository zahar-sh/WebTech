package com.example.fundamentals;

import com.example.fundamentals.utils.Input;

import java.util.Arrays;
import java.util.Scanner;

public class MergerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter sorted array");
        double[] a = Input.readArray(scanner);

        System.out.println("Enter sorted array");
        double[] b = Input.readArray(scanner);

        System.out.println("First array");
        System.out.println(Arrays.toString(a));

        System.out.println("Second array");
        System.out.println(Arrays.toString(b));

        Merger merger = new Merger(
                a, 0, a.length,
                b, 0, b.length,
                DoubleComparator.NORMAL);
        double[] merge = merger.merge();

        System.out.println("Merged array");
        System.out.println(Arrays.toString(merge));
    }
}
