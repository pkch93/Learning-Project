package edu.pkch.function

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class HigherOrderFunctionTestKt {

    @Test
    @DisplayName("함수를 리턴하는 고차 함수")
    internal fun returnFunction() {
        // given
        fun returnFunction(): (Int) -> Int = { it * 2 }

        // when
        val actual = returnFunction()(1)

        // then
        assertThat(actual).isEqualTo(2)
    }

    @Test
    @DisplayName("함수를 인자로 받는 고차 함수")
    internal fun argumentFunction() {
        // given
        fun argumentFunction(func: () -> Int): String {
            return func().toString()
        }

        // when
        val actual = argumentFunction { 20 }

        // then
        assertThat(actual).isEqualTo("20")
    }

    @ParameterizedTest
    @MethodSource("higherOrderFunction")
    @DisplayName("고차함수의 재사용성")
    internal fun higherOrderFunction(func: (Int, Int) -> Int, x: Int, y: Int, expected: Int) {
        // given
        fun higherOrder(operation: (Int, Int) -> Int, x: Int, y: Int): Int {
            return operation(x, y)
        }

        // when
        val actual = higherOrder(func, x, y)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("partialFunction")
    @DisplayName("부분함수 테스트")
    internal fun partialFunction(n: Int, expected: Int) {
        // given
        val condition: (Int) -> Boolean = { it in 1..10 }
        val func: (Int) -> Int = { it * it }

        val power: PartialFunction<Int, Int> = PartialFunction(condition, func)

        // when
        val actual = power(n)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    private companion object TestParams {
        val plus: (Int, Int) -> Int = { x, y -> x + y }
        val minus: (Int, Int) -> Int = { x, y -> x - y }
        val multiple: (Int, Int) -> Int = { x, y -> x * y }
        val divide: (Int, Int) -> Int = { x, y -> x / y }

        @JvmStatic
        fun higherOrderFunction() = listOf(
            Arguments { arrayOf(plus, 1, 2, 3) },
            Arguments { arrayOf(minus, 2, 1, 1) },
            Arguments { arrayOf(multiple, 2, 2, 4) },
            Arguments { arrayOf(divide, 4, 2, 2) }
        )

        @JvmStatic
        fun partialFunction() = listOf(
            Arguments { arrayOf(1, 1) },
            Arguments { arrayOf(2, 4) },
            Arguments { arrayOf(3, 9) },
            Arguments { arrayOf(4, 16) },
            Arguments { arrayOf(5, 25) },
            Arguments { arrayOf(6, 36) },
            Arguments { arrayOf(7, 49) },
            Arguments { arrayOf(8, 64) },
            Arguments { arrayOf(9, 81) },
            Arguments { arrayOf(10, 100) }
        )
    }

    @Test
    @DisplayName("부분 적용 함수")
    internal fun partialAppliedFunction() {
        // given
        val stringAdd: (String, String) -> String = { str1, str2 -> str1 + str2 }
        val partialHello: (String) -> String = stringAdd.partial("hello")

        // when
        val actual1 = partialHello(" world")
        val actual2 = partialHello(" pkch")

        // then
        assertThat(actual1).isEqualTo("hello world")
        assertThat(actual2).isEqualTo("hello pkch")
    }

    @Test
    @DisplayName("커링 함수")
    internal fun currying() {
        // given
        val plusThree = add(1)(2)

        // when
        val actual1 = plusThree(3)
        val actual2 = plusThree(5)

        // then
        assertThat(actual1).isEqualTo(6);
        assertThat(actual2).isEqualTo(8);
    }

    @Test
    @DisplayName("커링으로 로깅찍기")
    internal fun addCurryingLogging() {
        // given
        val logging: (Int) -> (Int) -> (Int) -> Int = { x ->
            { y ->
                { z ->
                    println("x: ${x}, y: ${y}, z: ${z}")
                    x + y + z
                }
            }
        }

        // when
        val actual = applyCurrying(1, 2, 3, logging)

        // then
        assertThat(actual).isEqualTo(6)
    }

    @Test
    @DisplayName("커링으로 합의 제곱 구하기")
    internal fun addCurryingLogging2() {
        // given
        val multiplication: (Int) -> (Int) -> (Int) -> Int = { x ->
            { y ->
                { z ->
                    (x + y).pow (2) + z
                }
            }
        }

        // when
        val actual = applyCurrying(1, 2, 3, multiplication)

        // then
        assertThat(actual).isEqualTo(12)
    }

    @Test
    @DisplayName("합성 함수")
    internal fun composeFunction() {
        // given
        val plusThree: (Int) -> Int = { i -> i + 3 }
        val plusFive: (Int) -> Int = { i -> i + 5 }

        val composed = plusThree compose plusFive

        // when
        val actual = composed(1)

        // then
        assertThat(actual).isEqualTo(9)
    }
}