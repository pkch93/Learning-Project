package edu.pkch.clazz

interface Interface {
    val a: Int // abstract

    var b: String

    fun hello() {
        println("hello interface")
    }
}

class Implementation(k: Int): Interface {
    override val a = k

    override var b = "hello"

    override fun hello() {
        super.hello()
        println("hello implementation")
    }
}

fun interface Operator {
    fun operate(x: Int, y: Int): Int
}

fun main() {
    val i = Implementation(1)

    i.b = "pkch"

    println(i.b)

    val plus = Operator { x, y -> x + y }
}

data class MemberDto(val firstName: String, val lastName: String) {
    var age: Int = 0
}

sealed class Seal {
    open class A(a: String) : Seal()
    class B(b: String) : Seal()
}

class C: Seal.A("hello")