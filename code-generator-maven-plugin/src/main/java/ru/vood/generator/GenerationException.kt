package ru.vood.generator

import java.util.Arrays.asList

class GenerationException(s: String, cause: Throwable?)
    : RuntimeException(
        s + if (cause != null)
            "\n 111Cause: " + cause.javaClass.toString() + " -> " + cause.message + "\nStack " +
                    asList(cause.stackTrace)
                            .stream()
                            .map { it.toString() }
                            .reduce { u: String, u2: String -> u + "\n" + u2 }
                            .get().toString()
        else ""
        , cause) {

/*
        init {
            super(s,cause)
        }
*/

    constructor(message: String) : this(message, null)

    override val message: String
        get() = super.message!!
}