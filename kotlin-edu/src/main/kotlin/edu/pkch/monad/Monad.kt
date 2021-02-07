package edu.pkch.monad

import edu.pkch.functor.Functor

interface Monad<out T>: Functor<T> {
    fun <V> pure(value: V): Monad<V>

    override fun <R> fmap(f: (T) -> R): Monad<R> = flatMap { a -> pure(f(a)) }

    infix fun <R> flatMap(f: (T) -> Monad<R>): Monad<R>

    infix fun <R> leadTo(m: Monad<R>): Monad<R> = flatMap { m }
}