package edu.pkch.control

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ControlKtTest {

    @Test
    internal fun triangulationTest() {
        // given
        val a = true

        // when
        val actual = if (a) 1 else 2

        // then
        val expect = 1
        assertThat(actual).isEqualTo(expect)
    }

    @Test
    internal fun blockTriangulationTest() {
        // given
        val a = true

        // when
        val actual = if (a) {
            // ...
            1
        } else {
            // ...
            2
        }

        // then
        val expect = 1
        assertThat(actual).isEqualTo(expect)
    }

    @Test
    internal fun whenControl() {
        // given
        val name = "pkch"

        // when
        val actual: Int
        when (name) {
            "pkch" -> actual = 1
            "park" -> actual = 2
            else -> actual = 0
        }

        // then
        assertThat(actual).isEqualTo(1)
    }
}