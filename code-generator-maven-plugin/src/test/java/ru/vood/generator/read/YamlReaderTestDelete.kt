package ru.vood.generator.read

import org.junit.jupiter.api.Test
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import ru.vood.generator.read.dto.KeyValDto
import ru.vood.generator.read.dto.TemplateParamDto

@Deprecated("on delete")
internal class YamlReaderTestDelete {


    @Test
    fun readTune() {

        val yaml = Yaml(Constructor(TemplateParamDto::class.java))
        val yamlDto = TemplateParamDto()
        val listOf = listOf(KeyValDto("1", "2"), KeyValDto("3", "4"))
        yamlDto.map = listOf
        yamlDto.multiMaps = listOf(KeyValDto("10", listOf(KeyValDto("100", "200"), KeyValDto("300", "400"))))
        val dump = yaml.dump(yamlDto)
        println(dump)

        val test = """
map:
- {key: '1', val: '2'}
- {key: '3', val: '4'}
multiMaps:
- key: '10'
  val:
  - {key: '100', val: '200'}
  - {key: '300', val: '400'} """

        val load = yaml.load<TemplateParamDto>(test)
        println("===>$load")
    }
}