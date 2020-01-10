package ru.vood.generator.file

import java.io.File


class FileReaderImpl : FileReader {

    override fun readFile(fileName: String): String {
        val resource = javaClass.classLoader.getResource(fileName)
        var file: File?
        if (resource != null)
            file = File(resource.path)
        else file = File(fileName)

//        Objects.requireNonNull(resource, "file $fileName is not found!")

        if (!file.exists()) {
//            println(File(fileName).readText())
            throw IllegalStateException("file ${file.absolutePath} is not found!")
        } else return file.readText()

    }

}