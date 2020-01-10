package ru.vood.generator.read

import java.io.File

interface TuneReader<T> {
    fun readTune(fileName: String): T

    fun saveTune(pluginTune: T, file: File)

}