package ru.vood.freemarker.util

import java.math.BigDecimal
import java.sql.Timestamp
import java.util.*
import kotlin.reflect.KClass

enum class TypeMapping(val sqlType: String, val javaClazz: KClass<*>, val getFromRs: String) {

    NUMBER("NUMBER", BigDecimal::class, "getLong"),
    DATE("DATE", Date::class, "getDate"),
    TIMESTAMP("TIMESTAMP", Timestamp::class, "getTimeStamp"),
    VARCHAR("VARCHAR", String::class, "getString")

}