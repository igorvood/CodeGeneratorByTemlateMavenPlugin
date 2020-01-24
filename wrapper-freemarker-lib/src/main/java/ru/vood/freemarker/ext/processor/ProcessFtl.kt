package ru.vood.freemarker.ext.processor

import freemarker.template.Template
import ru.vood.freemarker.ext.sql.FtlDefaultObjectWrapper
import java.io.Writer

interface ProcessFtl {

    fun getTemplate(templateName: String): Template

    fun getTemplate(clazz: Class<*>, templateName: String): Template

    fun getTemplateFromString(templateName: String, templateBody: String): Template

    fun process(template: Template, vararg args: Any?): String

    fun process(template: Template, dest: Writer, vararg args: Any?)

    fun process(templateName: String, vararg args: Any?): String = process(getTemplate(templateName), args)

    fun process(clazz: Class<*>, templateName: String, vararg args: Any?): String = process(getTemplate(clazz, templateName), args)

    fun process(templateName: String, templateBody: String, vararg args: Any?): String = process(getTemplateFromString(templateName, templateBody), args)

    fun process(templateName: String, param: Map<String, Any>): String {
        param.forEach { registerSharedVar(it.key, it.value) }
        return process(getTemplate(templateName))
    }

    fun process(clazz: Class<*>, templateName: String, param: Map<String, Any>): String {
        param.forEach { registerSharedVar(it.key, it.value) }
        return process(getTemplate(clazz, templateName))
    }

    fun process(templateName: String, templateBody: String, param: Map<String, Any>): String {
        param.forEach { registerSharedVar(it.key, it.value) }
        return process(getTemplateFromString(templateName, templateBody))
    }

    fun registerSharedVar(name: String, `val`: Any)

    fun getFtlDefaultObjectWrapper(): FtlDefaultObjectWrapper
}