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

    fun registerSharedVar(name: String, `val`: Any)

    fun getFtlDefaultObjectWrapper(): FtlDefaultObjectWrapper
}