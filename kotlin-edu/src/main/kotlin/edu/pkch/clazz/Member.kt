package edu.pkch.clazz

const val DEFAULT_PHONE = "000-0000-0000"

/*
 * 일반 클래스
 */
class Member(
    val name: String,
){
    val hello get() = "hello ${name}"
    var phone: String = DEFAULT_PHONE
        get() = field
        set(value) { field = value }
}

