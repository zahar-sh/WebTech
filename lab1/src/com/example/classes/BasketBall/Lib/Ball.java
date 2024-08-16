package com.example.classes.BasketBall.Lib;

import java.util.Objects;

public class Ball {
    private Color color;
    private int weight;

    public Ball() {
    }

    public Ball(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Ball ball = (Ball) o;
        return weight == ball.weight &&
                Objects.equals(color, ball.color);
    }

    @Override
    public int hashCode() {
        int hash = Objects.hashCode(color);
        hash = hash * 17 + weight;
        return hash ^ (hash >>> 16);
    }

    @Override
    public String toString() {
        return "Ball{color=" + color +
                ", weight=" + weight + '}';
    }
}
