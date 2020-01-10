package ru.vood.generator.file

import java.nio.file.Path

interface GenerateFile {

    fun generateFile(basePath: String, packageS: String, fileName: String, generatedCode: String): Path

}