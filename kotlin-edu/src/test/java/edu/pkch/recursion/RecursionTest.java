package edu.pkch.recursion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

class RecursionTest {

    @Test
    @DisplayName("명령형 프로그래밍으로 피보나치 구현")
    void fibonacci() {
        // given & when
        int actual = fibonacci(7);

        // then
        assertThat(actual).isEqualTo(13);
    }

    private static int fibonacci(int n) {
        int[] temp = new int[n + 1];
        temp[0] = 0;
        temp[1] = 1;

        for (int i = 2; i <= n; i++) {
            temp[i] = temp[i - 1] + temp[i - 2];
        }

        return temp[n];
    }

    @Test
    @DisplayName("재귀로 피보나치 구현")
    void recursionFibonacci() {
        // given & when
        int actual = recursionFibonacci(7);

        // then
        assertThat(actual).isEqualTo(13);
    }

    private static int recursionFibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return recursionFibonacci(n - 1) + recursionFibonacci(n - 2);
        }
    }

    @FunctionalInterface
    private interface TailRec {
        Function<Integer, Integer> recurse(int n);
    }

    @Test
    @DisplayName("메모이제이션으로 피보나치 구현")
    void memoizationFibonacci() {
        // given & when
        int actual = memoizationFibonacci(7);

        // then
        assertThat(actual).isEqualTo(13);
    }

    private static int[] cache = new int[5000];

    private static int memoizationFibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (cache[n] != 0) {
            return cache[n];
        } else {
            cache[n] = memoizationFibonacci(n - 1) + memoizationFibonacci(n - 2);
            return cache[n];
        }
    }
}
