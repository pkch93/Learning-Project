package edu.pkch.monad

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private class A1(val b: B1?)
private class B1(val c: C1?)
private class C1(val d: D1?)
private class D1(val value: String)

internal class MonadTest {
    @Test
    internal fun nested() {
        // given
        val a1 = A1(B1(C1(D1("hello world!"))))

        // when
        fun getValueOfA1(): String {
            val b = a1.b
            if (b != null) {
                val c = b.c
                if (c != null) {
                    val d = c.d
                    if (d != null) {
                        return d.value
                    } else {
                        return ""
                    }
                }
            }

            return ""
        }

        val actual = getValueOfA1()

        // then
        assertThat(actual).isEqualTo("hello world!")
    }
}

private class A2(val b: Maybe<B2>)
private class B2(val c: Maybe<C2>)
private class C2(val d: Maybe<D2>)
private class D2(val value: Maybe<String>)

internal class MonadTest2 {
    @Test
    internal fun nested() {
        // given
        val a2 = A2(Just(B2(Just(C2(Just(D2(Just("hello world!"))))))))

        // when
        fun getValueOfA2(): Maybe<String> =
            a2.b.flatMap { it.c }
                .flatMap { it.d }
                .flatMap { it.value }

        val actual = getValueOfA2()

        // then
        assertThat(actual.getValue()).isEqualTo("hello world!")
    }
}