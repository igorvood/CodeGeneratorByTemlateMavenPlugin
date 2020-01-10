package ru.vood.generator.generate.adapter

import ru.vood.generator.read.dto.TemplateParamDto

abstract class AbstractInputDataTypeToTemplateParamMapper<T> : InputDataTypeToTemplateParam<T> {
    override fun convert(param: T): TemplateParamDto {
        return try {
            map(param)
        } catch (e: Exception) {
            throw ConvertException("""Can not convert data, reason=>${e.message}""", e)
        }

    }

    protected abstract fun map(param: T): TemplateParamDto
}