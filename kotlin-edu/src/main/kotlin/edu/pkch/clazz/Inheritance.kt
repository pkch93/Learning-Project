package edu.pkch.clazz

open class Super {
    val a: String = ""
    open val b: String = ""
}

class Sub: Super() {
    //  override val a: String = "a"  compile error!
    override val b: String = "b"
}