package ru.vood.generator.xml

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class XMLValidatorImplTest {

    private lateinit var xmlValidator: XMLValidatorImpl

    @BeforeEach
    fun before() {
        xmlValidator = XMLValidatorImpl()
    }

    @Test
    fun validate() {
        xmlValidator.validate("XMLValidatorImplTest/test.xml", "XMLValidatorImplTest/test.xsd")
    }

    @Test
    fun validateError() {

        Assertions.assertThrows(XmlValidationException::class.java)
        { xmlValidator.validate("XMLValidatorImplTest/test.error.xml", "XMLValidatorImplTest/test.xsd") }

        Assertions.assertThrows(XmlValidationException::class.java
                , { xmlValidator.validate("XMLValidatorImplTest/test.error.xml", "XMLValidatorImplTest/test.xsd") }
                , "Expected other Exception")

    }
}