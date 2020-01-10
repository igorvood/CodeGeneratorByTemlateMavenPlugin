package ru.vood.generator.generate.adapter

import ru.vood.generator.read.dto.TemplateParamDto

interface InputDataTypeToTemplateParam<T> {

    fun convert(param: T): TemplateParamDto
}