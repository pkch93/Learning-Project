package edu.pkch.function

infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
    return { f -> this(g(f)) }
}