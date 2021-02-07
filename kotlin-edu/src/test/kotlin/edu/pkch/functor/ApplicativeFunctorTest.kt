package edu.pkch.functor

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ApplicativeFunctorTest {

    @Test
    @DisplayName("애플리케이티브 펑터 사용해보기")
    internal fun applicativeFunctor() {
        // given
        val maybe = ApplicativeJust(10)
        val makeDouble = ApplicativeJust<(Int) -> Int> { it * 2 }

        // when
        val actual = maybe apply makeDouble

        // then
        assertThat(actual).isExactlyInstanceOf(ApplicativeJust::class.java)
        assertThat(actual.getValue()).isEqualTo(20)
    }

    @Test
    @DisplayName("애플리케이티브 펑터 체이닝")
    internal fun applicativeFunctorChaining() {
        // given
        val maybe = ApplicativeJust(10)
        val makeDouble = ApplicativeJust<(Int) -> Int> { it * 2 }
        val minusTen = ApplicativeJust<(Int) -> Int> { it - 10 }
        val square = ApplicativeJust<(Int) -> Int> { it * it }

        // when
        val actual = maybe apply makeDouble apply minusTen apply square

        // then
        assertThat(actual).isExactlyInstanceOf(ApplicativeJust::class.java)
        assertThat(actual.getValue()).isEqualTo(100)
    }

    @Test
    @DisplayName("[애플리케이티브 펑터] 호출하는 애플리케이티브 펑터에 값이 함수일 때")
    internal fun applicativeFunctorChaining2() {
        // given
        val maybeMakeDouble = ApplicativeJust<(Int) -> Int> { it * 2 }
        val maybeTen = ApplicativeJust(10)

        // when
        val actual = maybeMakeDouble apply maybeTen

        // then
        assertThat(actual.getValue()).isEqualTo(20)
    }

    @Test
    @DisplayName("[애플리케이티브 펑터 체이닝] 커링활용")
    internal fun applicativeFunctorChaining3() {
        // given
        val maybeMultiple = ApplicativeJust<(Int) -> (Int) -> Int> { x -> { y -> x * y } }
        val maybeTen = ApplicativeJust(10)
        val maybeFive = ApplicativeJust(5)

        // when
        val actual = maybeMultiple apply maybeTen apply maybeFive

        // then
        assertThat(actual.getValue()).isEqualTo(50)
    }
}