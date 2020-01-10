package ru.vood.generator.file.resolve

interface FileNameResolver {

    fun resolveFileByContent(typeFile: TypeFile, text: String): FilePropertyDto
}