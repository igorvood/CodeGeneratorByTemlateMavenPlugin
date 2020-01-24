package ru.vood.freemarker.ext.processor

import freemarker.ext.beans.BeansWrapperBuilder
import freemarker.template.*
import org.springframework.util.Assert
import ru.vood.freemarker.ext.sql.FtlDefaultObjectWrapper
import ru.vood.freemarker.ext.sql.SqlFtlException
import java.io.File
import java.io.IOException
import java.io.StringWriter
import java.io.Writer
import java.nio.file.Files

abstract class AbstractFtlProcessor : Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS), ProcessFtl {

    override fun getTemplate(clazz: Class<*>, templateName: String): Template {
        return getTemplate("""${clazz.name.replace(".", "/")}/$templateName""")
    }

    override fun getTemplate(templateName: String): Template {
        var file = File(templateName)
        if (!file.exists()) {
            val resource = javaClass.classLoader.getResource(templateName)
                    ?: throw NoSuchFileException(file, null, "File $templateName not exists")
            file = File(resource.toURI())
            if (!file.exists())
                throw NoSuchFileException(file, null, "File $templateName not exists")
        }
        return getTemplateFromString(templateName, Files.readString(file.toPath()))
    }

    override fun getTemplateFromString(templateName: String, templateBody: String): Template {
        return try {
            Template(templateName, templateBody, this)
        } catch (e: IOException) {
            throw SqlFtlException("Unable to create template from pure ftl text", e)
        }
    }

    override fun process(template: Template, vararg args: Any?): String {
        val sw = StringWriter()
        process(template, sw, *args)
        return sw.toString()
    }

    override fun process(template: Template, dest: Writer, vararg args: Any?) {
        val root = SimpleHash(getFtlDefaultObjectWrapper())
        registerSharedVar("template_args", args)
        try {
            template.process(root, dest)
        } catch (e: IOException) {
            throw SqlFtlException("ftl processing exception", e)
        } catch (e: TemplateException) {
            throw SqlFtlException("ftl processing exception", e)
        }
    }

    override fun registerSharedVar(name: String, `val`: Any) {
        try {
            setSharedVariable(name, `val`)
        } catch (e: TemplateModelException) {
            throw SqlFtlException("Unable to register $name variable", e)
        }
    }

    override fun getFtlDefaultObjectWrapper(): FtlDefaultObjectWrapper {
        return objectWrapper as FtlDefaultObjectWrapper
    }

    protected open fun getGetStaticMethod(): TemplateMethodModelEx {
        return TemplateMethodModelEx { args: List<*> ->
            Assert.isTrue(args.size == 1) { "Wrong number of arguments: expected 1, got " + args.size }
            val classNameObj = args[0]!!
            Assert.isTrue(
                    classNameObj is TemplateScalarModel
            ) { "Illegal type of argument #1: expected string, got " + classNameObj.javaClass.name }
            BeansWrapperBuilder(incompatibleImprovements).build()
                    .staticModels[(classNameObj as TemplateScalarModel).asString]
        }
    }


}