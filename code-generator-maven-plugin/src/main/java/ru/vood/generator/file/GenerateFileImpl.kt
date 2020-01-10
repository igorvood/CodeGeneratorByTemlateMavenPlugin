package ru.vood.generator.file

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

class GenerateFileImpl : GenerateFile {

    override fun generateFile(basePath: String, packageS: String, fileName: String, generatedCode: String): Path {
        val path = Paths.get("${createDirs(basePath, packageS)}\\$fileName")
        return Files.write(path, generatedCode.toByteArray(), StandardOpenOption.CREATE)
                ?: throw IllegalStateException("Unable to write file $path")
    }

    private fun createDirs(startPath: String, packageName: String): String {
        val dirs = """$startPath\${packageName.replace(".", "\\")}"""
        val dir = Files.createDirectories(Paths.get(dirs))
        return dir.toString()
    }


}