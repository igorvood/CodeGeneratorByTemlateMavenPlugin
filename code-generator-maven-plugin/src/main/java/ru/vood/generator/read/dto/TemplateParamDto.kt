package ru.vood.generator.read.dto

data class TemplateParamDto(
        var map: List<KeyValDto<String>>
        , var multiMaps: List<KeyValDto<List<KeyValDto<String>>>>
        , var multiList: List<KeyValDto<List<String>>>
) {
    constructor() : this(ArrayList<KeyValDto<String>>(), ArrayList<KeyValDto<List<KeyValDto<String>>>>(), ArrayList<KeyValDto<List<String>>>())
}