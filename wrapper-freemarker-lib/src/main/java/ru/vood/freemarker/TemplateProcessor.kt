package ru.vood.freemarker

interface TemplateProcessor {

    fun process(fileName: String, vararg args: Any?): String

    fun process(clazz: Class<*>, fileName: String, vararg args: Any?): String

    fun process(templateName: String, templateBody: String, vararg args: Any?): String

    fun process(templateName: String, param: Map<String, *>): String

    fun process(clazz: Class<*>, templateName: String, param: Map<String, *>): String

    fun process(templateName: String, templateBody: String, param: Map<String, *>): String

}