package edu.pkch.variables

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StringKtTest {
    @Test
    internal fun stringToChar() {
        // given
        val string = "hello"

        // when
        val actual = string[0]

        // then
        assertThat('h').isEqualTo(actual)
    }

    @Test
    internal fun stringIterate() {
        // given
        val string = "hello"

        // when & then
        var i = 0
        for (s in string) {
            assertThat(string[i]).isEqualTo(s)
            i = i.inc()
        }
    }

    @Test
    internal fun multilineString() {
        // given & when
        val multilineString = """
             multiline
             string
        """

        val expected = "multiline\n" + "string"

        // then
        assertThat(multilineString).isEqualTo(expected)
    }

    @Test
    internal fun stringTemplate() {
        // given
        val name = "pkch"

        // when
        val actual = "hello ${name}"

        // then
        val expected = "hello pkch"
        assertThat(actual).isEqualTo(expected)
    }
}