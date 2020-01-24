package ru.vood.freemarker

interface TemplateProcessor {

    fun registerSharedVar(name: String, `val`: Any)

    fun process(fileName: String, vararg args: Any?): String

    fun process(clazz: Class<*>, fileName: String, vararg args: Any?): String

    fun process(templateName: String, templateBody: String, vararg args: Any?): String
    //---------------------------------

    fun process(templateName: String, param: Map<String, Any>): String {
        param.forEach { registerSharedVar(it.key, it.value) }
        return process(templateName)
    }

    fun process(clazz: Class<*>, templateName: String, param: Map<String, Any>): String {
        param.forEach { registerSharedVar(it.key, it.value) }
        return process(clazz, templateName)
    }

    fun process(templateName: String, templateBody: String, param: Map<String, Any>): String {
        param.forEach { registerSharedVar(it.key, it.value) }
        return process(templateName, templateBody)
    }

}