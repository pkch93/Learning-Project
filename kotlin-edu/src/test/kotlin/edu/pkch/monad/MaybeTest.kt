package edu.pkch.monad

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException

internal class MaybeTest {

    @Test
    @DisplayName("모나드 메이비 flatMap")
    internal fun monadFlatMap() {
        // given
        val just: Monad<Int> = Just(10)

        // when
        val actual = just.flatMap { x -> Just(x * 2) }

        // then
        assertThat(actual.getValue()).isEqualTo(20)
    }

    @Test
    @DisplayName("중첩 모나드 메이비 flatMap")
    internal fun nestedMonadFlatMap() {
        // given
        val just: Monad<Monad<Int>> = Just(Just(10))

        // when
        val actual = just.flatMap { x -> x.fmap { it * 2 } }

        // then
        assertThat(actual.getValue()).isEqualTo(20)
    }

    @Test
    @DisplayName("모나드 메이비 apply")
    internal fun monadApply() {
        // given & when
        val actual = Maybe.pure { x: Int -> x * 2 } apply Just(10)

        // then
        assertThat(actual.getValue()).isEqualTo(20)
    }

    @Test
    @DisplayName("모나드 메이비 nothing leadTo")
    internal fun monadNothingLeadTo() {
        // given & when
        val actual = Nothing.leadTo(Just(10))

        // then
        assertThatThrownBy { actual.getValue() }
            .isInstanceOf(IllegalStateException::class.java)
    }

    @Test
    @DisplayName("모나드 메이비 nothing flatMap")
    internal fun monadNothingFlatMap() {
        // given & when
        val actual = Nothing.flatMap { x: Int -> Just(x * 2) }

        // then
        assertThatThrownBy { actual.getValue() }
            .isInstanceOf(IllegalStateException::class.java)
    }
}