package edu.pkch.delegation

import kotlin.reflect.KProperty

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

class Example {
    var p: String by Delegate()
    val lazyHello: String by lazy {
        println("lazy hello")
        "hello world!"
    }
}

fun main() {
    val e = Example()
    println(e.p)
    println(e.lazyHello)
    println(e.lazyHello)
}