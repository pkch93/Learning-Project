package edu.pkch.functor

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

internal class FunctorTest {
    @Test
    @DisplayName("[Either] 100을 나눈다.")
    internal fun eitherDivide() {
        // given
        val i = 100
        val s = "10"

        // when
        val actual = divide(i, s)

        // then
        assertThat(actual.getValue()).isEqualTo(10)
    }

    @Test
    @DisplayName("[Either] 0으로 나눈다.")
    internal fun eitherDivideByZero() {
        // given
        val i = 100
        val s = "0"

        // when
        val actual = divide(i, s)

        // then
        assertThatThrownBy { actual.getValue() }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    @DisplayName("[Either] 문자열이 숫자 형태가 아니다.")
    internal fun eitherDivideByString() {
        // given
        val i = 100
        val s = "s"

        // when
        val actual = divide(i, s)

        // then
        assertThatThrownBy { actual.getValue() }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    @DisplayName("인자가 여러개인 functor")
    internal fun multipleParameterFunctor() {
        // given
        val multiple: (Int) -> (Int) -> Int = { x -> { y -> x * y } }

        // when
        val actual = Just(10).fmap(multiple)

        // then
        assertThat(actual.fmap { it(5) }.getValue()).isEqualTo(50)
    }
}
