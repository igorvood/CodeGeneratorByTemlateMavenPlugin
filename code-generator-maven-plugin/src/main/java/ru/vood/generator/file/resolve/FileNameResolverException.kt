package ru.vood.generator.file.resolve

class FileNameResolverException(s: String) : RuntimeException(s) {
    override val message: String
        get() = super.message!!
}