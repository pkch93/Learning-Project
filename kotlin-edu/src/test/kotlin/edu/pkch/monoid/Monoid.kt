package edu.pkch.monoid

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MonoidTest {

    @Test
    @DisplayName("덧셈 모노이드 법칙 성립 테스트")
    internal fun sumMonoid() {
        // given
        val sum = SumMonoid()

        // when
        val identity = sum.mappend(sum.mempty(), 1)
        val identity2 = sum.mappend(1, sum.mempty())

        val association = sum.mappend(sum.mappend(1, 2), 3)
        val association2 = sum.mappend(1, sum.mappend( 2, 3))

        // then
        assertThat(identity).isEqualTo(1)
        assertThat(identity2).isEqualTo(1)
        assertThat(identity).isEqualTo(identity2)

        assertThat(association).isEqualTo(association2)
    }

    @Test
    @DisplayName("곱셈 모노이드 법칙 성립 테스트")
    internal fun productMonoid() {
        // given
        val product = ProductMonoid()

        // when
        val identity = product.mappend(product.mempty(), 1)
        val identity2 = product.mappend(1, product.mempty())

        val association = product.mappend(product.mappend(1, 2), 3)
        val association2 = product.mappend(1, product.mappend( 2, 3))

        // then
        assertThat(identity).isEqualTo(1)
        assertThat(identity2).isEqualTo(1)
        assertThat(identity).isEqualTo(identity2)

        assertThat(association).isEqualTo(association2)
    }
}