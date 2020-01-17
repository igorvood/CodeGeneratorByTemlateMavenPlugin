package ru.vood.generator.generate

import org.apache.maven.plugin.logging.Log
import ru.vood.generator.file.FileReader
import ru.vood.generator.file.GenerateFile
import ru.vood.generator.file.getCanonicalPath
import ru.vood.generator.file.resolve.FileNameResolver
import ru.vood.generator.file.resolve.FilePropertyDto
import ru.vood.generator.generate.runner.resolveEngineRunner
import ru.vood.generator.read.YamlReader
import ru.vood.generator.read.dto.PluginParamDto
import ru.vood.generator.read.dto.TemplateParamDto
import java.io.File
import java.lang.RuntimeException
import java.util.*
import java.util.stream.Collectors
import kotlin.streams.toList


class ClassGenerator(val fileNameResolver: FileNameResolver, val generateFileImpl: GenerateFile, val fileReader: FileReader, val log: Log) {

    fun generate(pluginPropertyYamlFile: String, baseDirectory: String) {
        val genParam = getGenParam(pluginPropertyYamlFile)
        val textFiles = generateTextFiles(genParam)
        val files: List<Triple<GenerateParamWithYamlDto, String, FilePropertyDto>> = textFiles.stream()
                .map { p ->
                    p.second.split(p.first.classSeparator).stream()
                            .map { Triple(p.first, it, fileNameResolver.resolveFileByContent(p.first.classType, it)) }
                }
                .flatMap { it }
                .toList()
        val allItems = HashSet<FilePropertyDto>()

        val dublicate = files.stream()
                .filter { !allItems.add(it.third) }
                .map { Triple(it.first.templateParamFileFilesDto.templateFile, it.first.templateParamFileFilesDto.templateParamFile, it.third) }
                .collect(Collectors.toSet())
        if (dublicate.size > 0)
            throw IllegalStateException("Duplicate is found $dublicate")

        log.info("------- TJC plugin Generate files ---------------")
        files.stream()
                .peek { log.debug("""File prop ${it.third}""") }
                .peek { log.info("""Try to generate file by template ${it.first.templateParamFileFilesDto.templateFile} template param ${it.first.templateParamFileFilesDto.templateParamFile}""") }
                .map { generateFileImpl.generateFile(baseDirectory, it.third.packageStr, it.third.fileName, it.second) }
                .forEach { log.info("Generated file $it") }

        log.info("total files ${files.size}")
    }


    fun generateTextFiles(param: List<GenerateParamWithYamlDto>): List<Pair<GenerateParamWithYamlDto, String>> {
        return param.stream()
                .map { genrateText(it) }
                .toList()
    }

    fun genrateText(p: GenerateParamWithYamlDto): Pair<GenerateParamWithYamlDto, String> {
        val templateFile = p.templateParamFileFilesDto.templateFile
        val templateEngine = p.templateEngine

        try {
            val file = File(templateFile)
            if (!file.exists()) throw  RuntimeException("File '${templateFile}' does no exits")

            return Pair(p, resolveEngineRunner(templateEngine).generateText(p.templateParam, file))
        } catch (e: Exception) {
            throw IllegalStateException("Can not generate text for engine $templateEngine file $templateFile")
        }
    }

    fun getGenParam(pluginPropertyYamlFile: String): List<GenerateParamWithYamlDto> {

        val yamlReader = YamlReader(PluginParamDto::class.java, fileReader)
        val pluginParam = yamlReader.readTune(getCanonicalPath(File(pluginPropertyYamlFile)))
        val yamlTemplateParamDto = YamlReader(TemplateParamDto::class.java, fileReader)

        val toList = pluginParam.generateParamDto.stream()
                .map { p ->
                    p.templateParamFilesDto.stream()
                            .map {
                                GenerateParamWithYamlDto(
                                        p.templateEngine,
                                        p.classType,
                                        p.classSeparator,
                                        it,
                                        yamlTemplateParamDto.readTune(it.templateParamFile))
                            }
                }
                .flatMap { it }
                .toList()
        return toList
    }

}