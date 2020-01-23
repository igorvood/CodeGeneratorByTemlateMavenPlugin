package ru.vood.generator.generate.runner

import ru.vood.generator.GenerationException
import ru.vood.generator.read.dto.TemplateParamDto
import java.io.File

class ErrorEngine : RunnerEngine {
    override fun generateText(param: TemplateParamDto, templateFile: File): String {
        throw GenerationException("Unexpected engine")
    }
}
