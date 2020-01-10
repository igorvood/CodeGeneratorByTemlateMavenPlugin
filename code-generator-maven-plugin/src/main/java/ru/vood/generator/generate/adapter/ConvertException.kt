package ru.vood.generator.generate.adapter

class ConvertException(s: String, cause: Throwable?) : RuntimeException(s, cause) {

    constructor(message: String) : this(message, null)

    override val message: String
        get() = super.message!!
}