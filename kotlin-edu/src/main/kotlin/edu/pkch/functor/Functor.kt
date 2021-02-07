package edu.pkch.functor

import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import kotlin.Nothing

interface Functor<out P> {
    fun <R> fmap(f: (P) -> R): Functor<R>
    fun getValue(): P
}

sealed class Maybe<out P>: Functor<P> {
    abstract override fun <R> fmap(f: (P) -> R): Maybe<R>
}

data class Just<out P>(
    private val value: P
): Maybe<P>() {
    override fun <R> fmap(f: (P) -> R): Maybe<R> = Just(f(value))
    override fun getValue(): P = value
}

object Nothing: Maybe<Nothing>() {
    override fun <R> fmap(f: (Nothing) -> R): Maybe<R> = Nothing
    override fun getValue(): Nothing {
        throw IllegalArgumentException()
    }
}

sealed class Either<out L, out R>: Functor<R> {
    abstract override fun <R2> fmap(f: (R) -> R2): Either<L, R2>
}

data class Left<out L>(private val value: L): Either<L, Nothing>() {
    override fun <R2> fmap(f: (Nothing) -> R2): Either<L, Nothing> = this
    override fun getValue(): Nothing {
        throw IllegalArgumentException()
    }
}

data class Right<out R>(private val value: R): Either<Nothing, R>() {
    override fun <R2> fmap(f: (R) -> R2): Either<Nothing, R2> = Right(f(value))
    override fun getValue(): R = value
}

fun divide(i: Int, s: String): Either<String, Int> = try {
    Right(i / s.toInt())
} catch (e: NumberFormatException) {
    Left("${s}는 숫자 형식이 아닙니다.")
} catch (e: ArithmeticException) {
    Left("${s} 값으로 계산할 수 없습니다.")
} catch (e: Throwable) {
    Left("알시적으로 오류가 발생했습니다.")
}