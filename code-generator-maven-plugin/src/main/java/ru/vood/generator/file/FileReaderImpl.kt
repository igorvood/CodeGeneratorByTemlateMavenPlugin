package ru.vood.generator.file

import java.io.File


class FileReaderImpl : FileReader {

    override fun readFile(fileName: String): String {
        val resource = javaClass.classLoader.getResource(fileName)
        val file: File?
        file =
                if (resource != null) File(resource.path)
                else File(fileName)

        if (!file.exists()) {
            throw IllegalStateException("file ${file.absolutePath} is not found!")
        } else return file.readText()

    }

}