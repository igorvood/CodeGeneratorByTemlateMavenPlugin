package ru.vood.generator.read

import ru.vood.generator.xml.XMLValidator
import ru.vood.plugin.generated.from.xsd.PluginTines
import java.io.File
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.bind.Unmarshaller


class XmlReader(private val xmlValidator: XMLValidator) : TuneReader<PluginTines> {

    private val unmarshaller: Unmarshaller
    private val marshaller: Marshaller

    init {
        val jaxbContext = JAXBContext.newInstance(PluginTines::class.java)
        unmarshaller = jaxbContext.createUnmarshaller()
        marshaller = jaxbContext.createMarshaller()
    }

    override fun readTune(fileName: String): PluginTines {
        xmlValidator.validate(fileName)
        val unmarshal = unmarshaller.unmarshal(File(xmlValidator.getResource(fileName)))
        return (unmarshal as PluginTines)
    }

    override fun saveTune(pluginTune: PluginTines, file: File) {
        marshaller.marshal(pluginTune, file)
    }
}