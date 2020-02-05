package ru.vood.generator.generate.runner

import ru.vood.freemarker.TemplateProcessor
import ru.vood.generator.GenerationException
import ru.vood.generator.read.dto.TemplateParamDto
import java.io.File

class FreeMarkerEngine(val ftlProcessor: TemplateProcessor) : RunnerEngine {

    override fun generateText(param: TemplateParamDto, templateFile: File): String {
        try {
            val mapOf = mapOf<String, Any>(
                    "map" to param.map,
                    "multiList" to param.multiList,
                    "multiMaps" to param.multiMaps)
            return ftlProcessor.process(templateFile.absolutePath, mapOf)
        } catch (e: Exception) {
            throw GenerationException("Error process template file ${templateFile.absolutePath} with param\n $param", e)
        }
    }
}