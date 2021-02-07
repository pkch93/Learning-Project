package edu.pkch.function

fun add(x: Int): (Int) -> (Int) -> Int {
    return { y -> { z -> x + y + z } }
}

fun add(x: Int, y: Int, z: Int) = x + y + z

fun addLogging(x: Int, y: Int, z: Int): Int {
    println("x: ${x}, y: ${y}, z: ${z}")
    val result = add(x, y, z)
    println("result: ${result}")
    return result
}

fun addCurryingLogging(x: Int): (Int) -> (Int) -> Int {
    return { y ->
        { z ->
            println("x: ${x}, y: ${y}, z: ${z}")
            val result: Int = z + y + x
            println("result: ${result}")
            result
        }
    }
}

fun applyCurrying(x: Int, y: Int, z: Int, middleware: (Int) -> (Int) -> (Int) -> Int): Int {
    return middleware(x)(y)(z)
}
