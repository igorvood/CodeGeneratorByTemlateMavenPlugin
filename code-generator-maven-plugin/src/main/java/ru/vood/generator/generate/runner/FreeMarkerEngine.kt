package ru.vood.generator.generate.runner

import ru.vood.freemarker.TemplateProcessor
import ru.vood.generator.read.dto.TemplateParamDto
import java.io.File

class FreeMarkerEngine(val ftlProcessor: TemplateProcessor) : RunnerEngine {

    override fun generateText(param: TemplateParamDto, templateFile: File): String {
        try {
            println("FreeMarkerEngine templateFile" + templateFile.absolutePath)
            println("FreeMarkerEngine param" + param)
            return ftlProcessor.processFile(templateFile.absolutePath, arrayOf<Any?>(param))
        } catch (e: Exception) {
            throw IllegalStateException("Error process template file ${templateFile.absolutePath} with param\n $param")
        }
    }
}