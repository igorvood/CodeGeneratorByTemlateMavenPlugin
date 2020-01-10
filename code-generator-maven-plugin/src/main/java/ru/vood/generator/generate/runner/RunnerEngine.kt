package ru.vood.generator.generate.runner

import ru.vood.generator.read.dto.TemplateParamDto
import java.io.File

interface RunnerEngine {
    fun generateText(param: TemplateParamDto, templateFile: File): String
}
