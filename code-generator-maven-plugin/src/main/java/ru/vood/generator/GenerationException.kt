package ru.vood.generator

class GenerationException(s: String, cause: Throwable?) :
        RuntimeException(
                s + if (cause != null) "\n Cause: " + cause.javaClass + "\n" + cause.message else ""
                , cause
        ) {

    constructor(message: String) : this(message, null)

    override val message: String
        get() = super.message!!
}