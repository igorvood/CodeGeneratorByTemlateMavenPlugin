package ru.vood.generator.read.dto

import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import ru.vood.generator.file.resolve.TypeFile
import ru.vood.generator.generate.runner.TemplateEngine

enum class ExampleData(val clazz: Class<*>, val yaml: String) {
    TEMPLATE_PARAM(TemplateParamDto::class.java, yamlParamTemplateDto()),
    PLUGIN_PARAM(PluginParamDto::class.java, pluginParamDto());

}

fun yamlParamTemplateDto(): String {
    val yaml = Yaml(Constructor(TemplateParamDto::class.java))
    val yamlDto = TemplateParamDto()
    val listOf = listOf(KeyValDto("key1", "val2"), KeyValDto("key3", "val4"))
    yamlDto.map = listOf
    yamlDto.multiMaps = listOf(KeyValDto("key10", listOf(KeyValDto("key100", "val200"), KeyValDto("key300", "val400"))))
    yamlDto.multiList = listOf(
            KeyValDto("key1000", listOf("s1", "s2")),
            KeyValDto("key2000", listOf("s3", "s4"))
    )
    return yaml.dump(yamlDto)
}

private fun pluginParamDto(): String {
    val yaml = Yaml(Constructor(PluginParamDto::class.java))
    val yamlDto = PluginParamDto()

    yamlDto.generateParamDto = listOf(
            GenerateParamDto(TemplateEngine.FREE_MARKER, TypeFile.JAVA, "sep1", listOf(ParamForTemplateFilesDto("templateFile1", "paramFile1"), ParamForTemplateFilesDto("templateFile2", "paramFile2"))),
            GenerateParamDto(TemplateEngine.FREE_MARKER_DATABASE, TypeFile.KOTLIN, "sep2", listOf(ParamForTemplateFilesDto("templateFile1", "paramFile1"), ParamForTemplateFilesDto("templateFile2", "paramFile2"))),
            GenerateParamDto(TemplateEngine.VELOCITY, TypeFile.KOTLIN, "sep3", listOf(ParamForTemplateFilesDto("templateFile1", "paramFile1"), ParamForTemplateFilesDto("templateFile2", "paramFile2")))
    )
    return yaml.dump(yamlDto)
}
