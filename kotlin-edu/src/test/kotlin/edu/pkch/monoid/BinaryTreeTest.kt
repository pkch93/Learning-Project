package edu.pkch.monoid

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BinaryTreeTest {
    @Test
    internal fun foldableBinaryTree() {
        // given
        val tree = Node(
            1,
            Node(2, Node(3), Node(4)),
            Node(5, Node(6), Node(7)),
        )

        // when
        val foldLeftActual = tree.foldLeft(0) { a, b -> a + b }
        val foldMapActual = tree.foldMap({ a -> a * 2 }, SumMonoid())

        // then
        assertThat(foldLeftActual).isEqualTo(28)
        assertThat(foldMapActual).isEqualTo(56)
    }

    @Test
    internal fun contains() {
        // given
        val tree = Node(
            1,
            Node(2, Node(3), Node(4)),
            Node(5, Node(6), Node(7)),
        )

        // when
        val exist = tree.contain(1)
        val notExist = tree.contain(10)

        // then
        assertThat(exist).isEqualTo(true)
        assertThat(notExist).isEqualTo(false)
    }
}