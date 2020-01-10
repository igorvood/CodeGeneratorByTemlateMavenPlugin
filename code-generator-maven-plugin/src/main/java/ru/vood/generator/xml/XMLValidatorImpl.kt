package ru.vood.generator.xml

import org.xml.sax.SAXException
import java.io.File
import java.io.IOException
import java.util.*
import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

class XMLValidatorImpl : XMLValidator {


    override fun validate(xmlFile: String, schemaFile: String) {
        val schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
        try {
            val schema = schemaFactory.newSchema(File(getResource(schemaFile)))
            val validator = schema.newValidator()
            validator.validate(StreamSource(File(getResource(xmlFile))))
        } catch (e: SAXException) {
            throw XmlValidationException(e)
        } catch (e: IOException) {
            throw XmlValidationException("Unable to read xmlFile -> $xmlFile", e)
        }
    }

    override fun getResource(filename: String): String {
        val resource = javaClass.classLoader.getResource(filename)
        Objects.requireNonNull(resource, "file is not found!")
        return resource.file
    }
}