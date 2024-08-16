package com.example.classes.BasketBall.Lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Basket extends ArrayList<Ball> {
    public Basket(int initialCapacity) {
        super(initialCapacity);
    }

    public Basket() {
        super();
    }

    public Basket(Collection<? extends Ball> c) {
        super(c);
    }

    public int totalWeight() {
        return stream().
                mapToInt(Ball::getWeight).
                sum();
    }

    public int countByColor(Color color) {
        Objects.requireNonNull(color);
        return (int) stream().
                map(Ball::getColor).
                filter(color::equals).
                count();
    }
}
