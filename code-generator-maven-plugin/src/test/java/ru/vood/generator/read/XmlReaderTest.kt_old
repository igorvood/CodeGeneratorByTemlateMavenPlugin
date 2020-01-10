package ru.vood.generator.read

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.vood.generator.xml.XMLValidatorImpl
import ru.vood.plugin.generated.from.xsd.Generate
import ru.vood.plugin.generated.from.xsd.GenerateByTemplateList
import ru.vood.plugin.generated.from.xsd.PluginTines
import java.io.File

internal class XmlReaderTest {

    private lateinit var tuneReader: TuneReader<PluginTines>

    @BeforeEach
    fun setUp() {
        tuneReader = XmlReader(XMLValidatorImpl())
    }

    @Test
    fun readTune() {
        val readTune = tuneReader.readTune("TuneReaderImplTest/ExampleParameters.xsd.xml")
        Assertions.assertNotNull(readTune)
        Assertions.assertEquals(2, readTune.templateGenerateList.generate.size)
    }

    @Test
    fun saveTune() {
        val pluginTinesType = PluginTines()
        pluginTinesType.templateGenerateList = GenerateByTemplateList()
        val generate = pluginTinesType.templateGenerateList.generate
        val element = Generate()
        element.fileNameParam = "fileNameParam"
        element.metaInfoXmlFile = "metaInfoXmlFile"
        element.`package` = "package"
        element.xsdForMetaInfoXmlFile = "xsdForMetaInfoXmlFile"
        generate.add(element)
        tuneReader.saveTune(pluginTinesType, File("Test.xml"))
    }

}