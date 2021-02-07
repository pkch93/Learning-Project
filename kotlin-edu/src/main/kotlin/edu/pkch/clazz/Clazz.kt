package edu.pkch.clazz

class Clazz(name: String) {
    init {
        println("hello ${name}")
    }

    val uppercaseName = name.toUpperCase()

    init {
        println("hello ${uppercaseName}")
    }
}

