package edu.pkch.monad

sealed class Maybe<out T>: Monad<T> {
    companion object {
        fun <V> pure(value: V): Maybe<V> = Just(0).pure(value)
    }

    override fun <R> fmap(f: (T) -> R): Maybe<R> = super.fmap(f) as Maybe<R>

    override fun <V> pure(value: V): Maybe<V> = Just(value)

    override infix fun <R> flatMap(f: (T) -> Monad<R>): Maybe<R> = when(this) {
        is Just -> try {
            f(getValue()) as Maybe<R>
        } catch (e: ClassCastException) {
            Nothing
        }
        is Nothing -> Nothing
    }
}

infix fun <T, R> Maybe<(T) -> R>.apply(f: Maybe<T>): Maybe<R> = when(this) {
    is Just -> f.fmap(getValue())
    is Nothing -> Nothing
}

data class Just<out T>(private val value: T): Maybe<T>() {
    override fun getValue(): T = value

    override fun <V> pure(value: V): Maybe<V> = Just(value)
}

object Nothing: Maybe<kotlin.Nothing>() {
    override fun getValue(): kotlin.Nothing {
        throw IllegalStateException()
    }
}