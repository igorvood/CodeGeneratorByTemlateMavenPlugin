package ru.vood.generator.read.dto

data class KeyValDto<T>(
        var key: String?
        , var `val`: T?
) {
    constructor() : this(null, null)
}