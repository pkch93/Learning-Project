package edu.pkch.delegation

interface Base {
    val message: String
    fun print()
    fun printMessage()
    fun printMessageLine()
}

class BaseImpl(val x: Int) : Base {
    override val message = "hello base!"
    override fun print() { println(message) }
    override fun printMessage() { print(x) }
    override fun printMessageLine() { println(x) }
}

class Derived(b: Base) : Base by b {
    override val message = "hello derived!"
    override fun printMessage() { print("abc") }
}

fun main() {
    val b = BaseImpl(10)
    val d = Derived(b)
    b.print()
    d.print()
}
