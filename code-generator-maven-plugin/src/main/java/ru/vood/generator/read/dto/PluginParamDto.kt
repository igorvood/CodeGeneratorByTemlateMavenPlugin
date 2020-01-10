package ru.vood.generator.read.dto

data class PluginParamDto(var generateParamDto: List<GenerateParamDto>
) {
    constructor() : this(ArrayList<GenerateParamDto>())
}