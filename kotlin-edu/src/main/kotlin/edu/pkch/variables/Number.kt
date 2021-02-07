package edu.pkch.variables

fun variable() {
    val a = 1 // val은 immutable

    var b = 1 // 코틀린 컴파일러가 타입 추론을 해줌
    b += 1

    val c: Int // 단, 초기 할당을 안한다면 타입 명시가 필요

    val d = 1.6 // Double은 소수점

    val e = 1.6f // float는 f를 붙여주어야함.

    val aIsBetweenZeroToTwo = a in 0..2 // true

    val f = 1 shl 1

    val g = 1 shr 1

    val h = 0 and 1

    val i = 0 or 1
}
