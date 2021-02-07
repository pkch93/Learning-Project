package edu.pkch.recursion

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RecursionKtTest {

    @Test
    internal fun functionalMemoizationForNotTailCall() {
        // given & when
        val actual = functionalMemoizationForNotTailCall(7)

        // then
        assertThat(actual).isEqualTo(13);
    }

    fun functionalMemoizationForNotTailCall(n: Int, first: Int = 0, second: Int = 1): Int {
        val i = when (n) {
            0 -> first
            1 -> second
            else -> functionalMemoizationForNotTailCall(n - 1, second, first + second)
        }

        return i
    }

    @Test
    internal fun functionalMemoization() {
        // given & when
        val actual = functionalMemoization(7)

        // then
        assertThat(actual).isEqualTo(13);
    }

    tailrec fun functionalMemoization(n: Int, first: Int = 0, second: Int = 1): Int = when (n) {
        0 -> first
        1 -> second
        else -> functionalMemoization(n - 1, second, first + second)
    }

    @Test
    internal fun functionalFactorial() {
        // given & when
        val actual = functionalFactorial(5)

        // then
        assertThat(actual).isEqualTo(120)
    }

    tailrec fun functionalFactorial(n: Int, result: Int = 1): Int = when (n) {
        0 -> result
        else -> functionalFactorial(n - 1, result * n)
    }
}