package ru.vood.generator.xml

interface XMLValidator {
    public val
            DEFAULT_XSD_SHEME: String
        get() = "XSDSchema/code-generator-param-1.0.xsd"

    fun validate(xmlFile: String, schemaFile: String = DEFAULT_XSD_SHEME)

    fun getResource(filename: String): String

}