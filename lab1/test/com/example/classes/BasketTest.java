package com.example.classes;

import com.example.classes.BasketBall.Lib.Ball;
import com.example.classes.BasketBall.Lib.Basket;
import com.example.classes.BasketBall.Lib.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasketTest {

    @Test
    void totalWeight() {
        Basket basket = new Basket();
        basket.add(new Ball(Color.BLUE, 11));
        basket.add(new Ball(Color.RED, 13));
        basket.add(new Ball(Color.BLACK, 41));
        basket.add(new Ball(Color.BLUE, 12));
        basket.add(new Ball(Color.YELLOW, 32));

        Assertions.assertEquals(basket.totalWeight(), 11 + 13 + 41 + 12 + 32);

        basket.add(new Ball(Color.BLUE, 9));
        Assertions.assertEquals(basket.totalWeight(), 11 + 13 + 41 + 12 + 32 + 9);

        basket.remove(0);
        Assertions.assertEquals(basket.totalWeight(), 13 + 41 + 12 + 32 + 9);
    }

    @Test
    void countByColor() {
        Basket basket = new Basket();
        basket.add(new Ball(Color.BLUE, 11));
        basket.add(new Ball(Color.RED, 13));
        basket.add(new Ball(Color.BLACK, 41));
        basket.add(new Ball(Color.BLUE, 12));
        basket.add(new Ball(Color.YELLOW, 32));
        basket.add(new Ball(Color.BLUE, 9));

        Assertions.assertEquals(basket.countByColor(Color.VIOLET), 0);
        Assertions.assertEquals(basket.countByColor(Color.BLUE), 3);
        Assertions.assertEquals(basket.countByColor(Color.RED), 1);
        Assertions.assertEquals(basket.countByColor(Color.BLACK), 1);
    }
}