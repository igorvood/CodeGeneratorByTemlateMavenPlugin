package ru.vood.generator.file.resolve

interface FileNameResolver {

    fun resolveFileByContent(typeFile: TypeFile, text: String, templateFile: String, paramFile: String): FilePropertyDto
}