package ru.vood.freemarker.util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class UtilMethodsForTemplateUseTest {

    @Test
    fun toCamelCase() {
        val message = UtilMethodsForTemplateUse.toCamelCase("QWERTY_ASDFG_z")
        Assertions.assertEquals("QwertyAsdfgZ", message)
    }

    @Test
    fun toCamelCaseFirstLetterLower() {
        val message = UtilMethodsForTemplateUse.toCamelCaseFirstLetterLower("QWERTY_ASDFG_z")
        Assertions.assertEquals("qwertyAsdfgZ", message)

    }

    @Test
    fun sqlToJavaTypeMapping() {
        Assertions.assertEquals("java.math.BigDecimal", UtilMethodsForTemplateUse.sqlToJavaTypeMapping("NUMBER"))
        Assertions.assertEquals("java.math.BigDecimal", UtilMethodsForTemplateUse.sqlToJavaTypeMapping("NUMBER(2)"))
        Assertions.assertEquals("java.util.Date", UtilMethodsForTemplateUse.sqlToJavaTypeMapping("TIMESTAMP(2)"))
        Assertions.assertEquals("java.lang.String", UtilMethodsForTemplateUse.sqlToJavaTypeMapping("VARCHAR2(2)"))
    }
}