package ru.vood.generator.generate.runner

import ru.vood.freemarker.TemplateProcessor
import ru.vood.generator.read.dto.TemplateParamDto
import java.io.File

class FreeMarkerDatabaseEngine(val ftlProcessor: TemplateProcessor) : RunnerEngine {
    override fun generateText(param: TemplateParamDto, templateFile: File): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
