package edu.pkch.variables

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ArrayKtTest {
    @Test
    internal fun createByArrayOf() {
        // given & when
        val targetArray = arrayOf(1, 2, 3)

        // then
        assertThat(targetArray).containsOnly(1, 2, 3)
    }

    @Test
    internal fun createByConstructor() {
        // given & when
        val targetArray = Array(3) { i -> i + 1 }

        // then
        assertThat(targetArray).containsOnly(1, 2, 3)

    }
}