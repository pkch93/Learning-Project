package edu.pkch.clazz

fun helloByPerson(p: Person) {
    println(p.hello())
}

open class Person {
    open fun hello() = "hello!"
}

fun obj() {
    val objectInstance = object {
        val x: Int = 1
        val y: Int = 2
    }

    println("${objectInstance.x} + ${objectInstance.y} = ${objectInstance.x + objectInstance.y}")
}

fun main() {
    helloByPerson(object: Person() {
        override fun hello(): String {
            return "hello pkch!"
        }
    })
}