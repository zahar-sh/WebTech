package com.example.fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PrimeTest {

    @Test
    void primeNumberIndexes() {
        int[] numbers = {2, 4, 3, 7, 6, 9, 11, 23, 66, 54, 41, 123412400};
        int[] expect = {0, 2, 3, 6, 7, 10};
        Assertions.assertArrayEquals(expect, new Prime(numbers).primeNumberIndexes());
    }

    @Test
    void isPrime() {
        int[] primes = {2, 3, 7, 11, 23, 41};
        for (int prime : primes)
            Assertions.assertTrue(Prime.isPrime(prime));

        int[] notPrimes = {4, 6, 9, 66, 54, 123412400};
        for (int notPrime : notPrimes)
            Assertions.assertFalse(Prime.isPrime(notPrime));
    }
}