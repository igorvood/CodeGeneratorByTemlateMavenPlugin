package ru.vood.generator.read.dto

data class ParamForTemplateFilesDto(
        var templateFile: String
        , var templateParamFile: String
) {
    constructor() : this("Error templateFile", "error templateParamFile")
}