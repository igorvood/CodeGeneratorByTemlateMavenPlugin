package ru.vood.generator.generate.adapter

import ru.vood.generator.read.dto.TemplateParamDto

class YamlDataToTemplateParamMapper : AbstractInputDataTypeToTemplateParamMapper<TemplateParamDto>() {

    override fun map(param: TemplateParamDto): TemplateParamDto {
        return param
    }
}