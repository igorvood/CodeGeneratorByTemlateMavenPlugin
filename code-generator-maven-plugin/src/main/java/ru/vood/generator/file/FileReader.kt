package ru.vood.generator.file

interface FileReader {

    fun readFile(fileName: String): String
}