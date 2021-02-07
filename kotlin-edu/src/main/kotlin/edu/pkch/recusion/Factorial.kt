package edu.pkch.recusion

fun factorial(num: Int): Int {
    return when (num) {
        1 -> 1
        else -> num * factorial(num - 1)
    }
}

tailrec fun functionalFactorial(num: Long, result: Long = 1): Long = when (num) {
    1L -> result
    else -> functionalFactorial(num - 1, result * num)
}

tailrec fun maximumTest(numbers: List<Int>, max: Int): Int = when {
    numbers.isEmpty() -> max
    else -> {
        val temp = numbers.get(0)
        val subList = numbers.subList(1, numbers.size)
        val bigger = if (max <= temp) temp else max
        maximumTest(subList, bigger)
    }
}

fun maximum2(numbers: List<Int>): Int {
    println("maximum2")
    return when (numbers.size) {
        1 -> numbers[0]
        else -> {
            val subList = numbers.subList(1, numbers.size)
            val max = maximum2(subList)
            println("numbers: ${numbers}, max: ${max}")
            if (max >= numbers[0]) max else numbers[0]
        }
    }
}


fun main() {
//    println(functionalFactorial(5))
//    println(factorial(5))
    println(maximumTest(listOf(4, 6, 9, 3, 110, 5, 2), Int.MIN_VALUE))
    println(maximum2(listOf(4, 6, 9, 3, 110, 5, 2)))
}