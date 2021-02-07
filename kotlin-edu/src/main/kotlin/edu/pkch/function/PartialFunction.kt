package edu.pkch.function

import java.lang.IllegalArgumentException

class PartialFunction<in P, out R>(
    private val condition: (P) -> Boolean,
    private val func: (P) -> R
): (P) -> R {
    override fun invoke(p1: P): R = when {
        condition(p1) -> func(p1)
        else -> throw IllegalArgumentException("${p1}은 허용되지 않은 값입니다.")
    }
}
