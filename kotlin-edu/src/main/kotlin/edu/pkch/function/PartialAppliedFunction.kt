package edu.pkch.function

fun <P1, P2, R> ((P1, P2) -> R).partial(p1: P1): (P2) -> R {
    return { p2 -> this(p1, p2) }
}

fun <P1, P2, R> ((P1, P2) -> R).backPartial(p2: P2): (P1) -> R {
    return { p1 -> this(p1, p2) }
}
