package ru.vood.generator.read

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import ru.vood.generator.file.FileReader
import ru.vood.generator.read.dto.ExampleData
import java.io.File
import java.util.stream.Stream


class YamlReader<T>(private val clazz: Class<T>
                    , private val fileReader: FileReader) : TuneReader<T> {

    private val yaml = Yaml(Constructor(clazz))

    override fun readTune(fileName: String): T {
        val readFile = fileReader.readFile(fileName)
        return try {
            return yaml.load<T>(readFile) ?: throw IllegalStateException("File $fileName is empty")

        } catch (e: Exception) {
            val example = Stream.of(*ExampleData.values())
                    .filter { it.clazz == clazz }
                    .findFirst()
                    .get()
            throw IllegalStateException(
                    "The file format $fileName is not as expected. Example:\n${example.yaml}"
                    , e)
        }
    }

    override fun saveTune(pluginTune: T, file: File) {
        val text = yaml.dump(pluginTune)
        file.writeText(text)
    }

}