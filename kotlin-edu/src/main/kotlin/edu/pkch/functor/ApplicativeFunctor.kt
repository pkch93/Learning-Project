package edu.pkch.functor

interface ApplicativeFunctor<out P>: Functor<P> {
    fun <V> pure(value: V): ApplicativeFunctor<V>

    infix fun <R> apply(ff: ApplicativeFunctor<(P) -> R>): ApplicativeFunctor<R>
}

sealed class ApplicativeMaybe<out P>: ApplicativeFunctor<P> {
    abstract override fun <V> pure(value: V): ApplicativeMaybe<V>

    abstract override fun <R> fmap(f: (P) -> R): ApplicativeMaybe<R>

    abstract override fun <R> apply(ff: ApplicativeFunctor<(P) -> R>): ApplicativeMaybe<R>
}

infix fun <A, B> ApplicativeMaybe<(A) -> B>.apply(a: ApplicativeMaybe<A>): ApplicativeMaybe<B> = a.fmap(this.getValue())

data class ApplicativeJust<out P>(private val value: P): ApplicativeMaybe<P>() {
    override fun <V> pure(value: V): ApplicativeJust<V> = ApplicativeJust(value)

    override fun <R> fmap(f: (P) -> R): ApplicativeJust<R> = ApplicativeJust(f(value))

    override fun <R> apply(ff: ApplicativeFunctor<(P) -> R>): ApplicativeJust<R> = fmap(ff.getValue())

    override fun getValue(): P = value
}
