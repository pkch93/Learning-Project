package edu.pkch.clazz

internal class Outer {
    private val a: Int = 1

    fun thisIs() {
        println("outer")
    }

    class Nested {
        fun thisIs() {
            println("nested")
        }
    }

    inner class Inner {
        fun thisIs() {
            this@Outer.thisIs()
            println("inner and access a, a is ${a}")
        }
    }
}

fun main() {
    val a = Outer()
}