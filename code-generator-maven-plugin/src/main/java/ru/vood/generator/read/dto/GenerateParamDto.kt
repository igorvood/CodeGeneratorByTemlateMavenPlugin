package ru.vood.generator.read.dto

import ru.vood.generator.file.resolve.TypeFile
import ru.vood.generator.generate.runner.TemplateEngine

data class GenerateParamDto(
        var templateEngine: TemplateEngine
        , var classType: TypeFile
        , var classSeparator: String
        , var templateParamFilesDto: List<ParamForTemplateFilesDto>
) {
    constructor() : this(TemplateEngine.ERROR, TypeFile.KOTLIN, "ERROR TEMPLATE PARAMETER FILE", arrayListOf<ParamForTemplateFilesDto>(ParamForTemplateFilesDto()))
}