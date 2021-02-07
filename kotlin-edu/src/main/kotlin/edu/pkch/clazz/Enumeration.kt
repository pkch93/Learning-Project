package edu.pkch.clazz

enum class Color(val rgb: String): Printer {
    Red("#ff0000") {
        override val order: Int = 1
        override fun print() {
            println(this.rgb)
        }
    },
    Blue("#00ff00"){
        override val order: Int = 2
        override fun print() {
            println(this.rgb)
        }
    },
    Green("#0000ff"){
        override val order: Int = 3
        override fun print() {
            println(this.rgb)
        }
    };

    abstract val order: Int
}
