package edu.pkch.variables

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class NumberKtTest {

    @Test
    internal fun number() {
        // given
        val a = 0

        // when
        val actual = a in 0..10

        // then
        assertTrue(actual)
    }
}