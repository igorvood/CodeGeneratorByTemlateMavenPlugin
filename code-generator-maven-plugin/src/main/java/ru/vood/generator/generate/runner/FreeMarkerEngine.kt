package ru.vood.generator.generate.runner

import ru.vood.freemarker.TemplateProcessor
import ru.vood.generator.GenerationException
import ru.vood.generator.read.dto.TemplateParamDto
import java.io.File

class FreeMarkerEngine(val ftlProcessor: TemplateProcessor) : RunnerEngine {

    override fun generateText(param: TemplateParamDto, templateFile: File): String {
        try {
            ftlProcessor.registerSharedVar("map", param.map)
            ftlProcessor.registerSharedVar("multiList", param.multiList)
            ftlProcessor.registerSharedVar("multiMaps", param.multiMaps)
            return ftlProcessor.processFile(templateFile.absolutePath)
        } catch (e: Exception) {
            throw GenerationException("Error process template file ${templateFile.absolutePath} with param\n $param", e)
        }
    }
}