package ru.vood.freemarker.ext.processor

class JdbcDriverNotFoundException(message: String, cause: Exception?) : RuntimeException(message, cause) {

    constructor(message: String) : this(message, null)

    override val message: String
        get() = super.message!!
}