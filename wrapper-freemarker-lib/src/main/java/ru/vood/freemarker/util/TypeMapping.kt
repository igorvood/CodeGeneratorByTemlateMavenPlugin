package ru.vood.freemarker.util

import java.math.BigDecimal
import java.util.*

enum class TypeMapping(val sqlType: String, val javaClazz: Class<*>) {

    NUMBER("NUMBER", BigDecimal::class.java),
    DATE("DATE", Date::class.java),
    TIMESTAMP("TIMESTAMP", Date::class.java),
    VARCHAR("VARCHAR", java.lang.String::class.java)

}