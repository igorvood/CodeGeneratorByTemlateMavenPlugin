package ru.vood.generator

class GenerationException(s: String, cause: Throwable?) : RuntimeException(s, cause) {

    constructor(message: String) : this(message, null)

    override val message: String
        get() = super.message!!
}