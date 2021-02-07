package edu.pkch.function

import edu.pkch.clazz.Outer

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sumInline(a: Int, b: Int) = a + b

// 기본적으로 return 타입이 없다면 Unit으로 설정된다.
fun printHello() {
    println("hello")
}

typealias StringProps = (arg: String) -> String

fun returnFunction(): StringProps {
    val a: (str: String) -> String = { str -> str }

    return a
}


fun hello(name: String): String {
    fun world():String {
        return "${name} hello world!"
    }

    return world()
}

fun String.lastChar(): Char = this[this.length - 1]

infix fun Int.pow(n: Int): Int {
    var result = this
    for (i in 1 until n) {
        result *= this
    }
    return result
}

private typealias Predicate<T> = (T) -> Boolean

fun main() {
    val isEven: Predicate<Int> = { it % 2 == 0 }
    val isEmpty: Predicate<String> = { it == "" }

    println(isEven(1))
    println(isEmpty(""))
}