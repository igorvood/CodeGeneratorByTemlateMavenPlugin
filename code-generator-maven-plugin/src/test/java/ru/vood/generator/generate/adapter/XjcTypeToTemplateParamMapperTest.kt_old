package ru.vood.generator.generate.adapter

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import ru.vood.plugin.generated.from.xsd.*

internal class XjcTypeToTemplateParamMapperTest {
    lateinit var xjcTypeToMap: InputDataTypeToTemplateParam<TemplateParam>

    @BeforeEach
    fun setUp() {
        xjcTypeToMap = XjcTypeToTemplateParamMapper()
    }

    @Test
    fun convert() {
        val templateParam = TemplateParam()
        templateParam.params = MapsType()
        templateParam.params.simpleMap = MapType()
        val superMapType = SuperMapType()
        templateParam.params.superMap = superMapType
        //-----------------------
        var element = EntryType()
        element.key = "elementKey1"
        element.value = "elementValue1"
        templateParam.params.simpleMap.entry.add(element)
        element = EntryType()
        element.key = "elementKey2"
        element.value = "elementValue2"
        templateParam.params.simpleMap.entry.add(element)

        //-----------------
        var set = SuperEntryType()
        set.key = "SuperEntryType1"
        set.value = templateParam.params.simpleMap
        templateParam.params.superMap.entry.add(set)
        set = SuperEntryType()
        set.key = "SuperEntryType2"
        set.value = templateParam.params.simpleMap
        templateParam.params.superMap.entry.add(set)

        val convert = xjcTypeToMap.convert(templateParam)
        Assertions.assertEquals(
                "TemplateParamDto(map=[KeyValDto(key=elementKey1, val=elementValue1), KeyValDto(key=elementKey2, val=elementValue2)], multiMaps=[KeyValDto(key=SuperEntryType1, val=[KeyValDto(key=elementKey1, val=elementValue1), KeyValDto(key=elementKey2, val=elementValue2)]), KeyValDto(key=SuperEntryType2, val=[KeyValDto(key=elementKey1, val=elementValue1), KeyValDto(key=elementKey2, val=elementValue2)])], multiList=[])",
                convert.toString()
        )
    }

    @Test
    @Disabled
    fun convertDuplicateError() {
        val templateParam = TemplateParam()
        templateParam.params = MapsType()
        templateParam.params.simpleMap = MapType()
        val superMapType = SuperMapType()
        templateParam.params.superMap = superMapType
        //-----------------------
        var element = EntryType()
        element.key = "elementKey1"
        element.value = "elementValue1"
        templateParam.params.simpleMap.entry.add(element)
        element = EntryType()
        element.key = "elementKey1"
        element.value = "elementValue2"
        templateParam.params.simpleMap.entry.add(element)

        try {
            xjcTypeToMap.convert(templateParam)
            Assertions.fail<String>("Test fail")
        } catch (e: ConvertException) {
            Assertions.assertTrue(e.message.contains("Can not convert data for simpleMap, reason=>"))
        } catch (e: Throwable) {
            Assertions.fail<String>("Test fail")
        }

    }

}