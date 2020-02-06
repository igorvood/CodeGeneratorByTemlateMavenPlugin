package ru.vood.freemarker.ext.processor

import freemarker.template.*
import ru.vood.freemarker.ext.sql.SqlFtlException
import java.io.File
import java.io.IOException
import java.io.StringWriter
import java.io.Writer
import java.nio.file.Files

abstract class AbstractFtlProcessor(param: Map<String, Any>) : Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS), ProcessFtl {

    val param: ThreadLocal<Map<String, *>> = ThreadLocal()
    val templateArg: ThreadLocal<Any> = ThreadLocal()

    init {
        param.entries.forEach { registerSharedVar(it.key, it.value) }
        registerSharedVar("template_param", getParamMethod())
        registerSharedVar("template_args", getTemplateArgMethod())
    }

    private fun getTemplateArgMethod(): TemplateMethodModelEx {
        return TemplateMethodModelEx { templateArg.get() }
    }

    private fun getParamMethod(): TemplateMethodModelEx {
        return TemplateMethodModelEx { param.get() }
    }

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
        templateArg.set(args)
        try {
            template.process(root, dest)
        } catch (e: IOException) {
            throw SqlFtlException("ftl processing exception", e)
        } catch (e: TemplateException) {
            throw SqlFtlException("ftl processing exception", e)
        }
    }

    /*можно использовать только в конструкторе, иначе все перестает быть потоко безоопастным*/
    protected fun registerSharedVar(name: String, `val`: Any) {
        try {
            setSharedVariable(name, `val`)
        } catch (e: TemplateModelException) {
            throw SqlFtlException("Unable to register $name variable", e)
        }
    }

    override fun getFtlDefaultObjectWrapper(): ObjectWrapper {
        return objectWrapper
    }

//    companion object {
//        protected fun getGetStaticMethod(): TemplateMethodModelEx {
//            return TemplateMethodModelEx { args: List<*> ->
//                Assert.isTrue(args.size == 1) { "Wrong number of arguments: expected 1, got " + args.size }
//                val classNameObj = args[0]!!
//                Assert.isTrue(
//                        classNameObj is TemplateScalarModel
//                ) { "Illegal type of argument #1: expected string, got " + classNameObj.javaClass.name }
//                BeansWrapperBuilder(incompatibleImprovements).build()
//                        .staticModels[(classNameObj as TemplateScalarModel).asString]
//            }
//        }
//    }

    override fun process(templateName: String, param: Map<String, *>): String {
        this.param.set(param)
        return process(getTemplate(templateName))
    }

    override fun process(clazz: Class<*>, templateName: String, param: Map<String, *>): String {
        this.param.set(param)
        return process(getTemplate(clazz, templateName))
    }

    override fun process(templateName: String, templateBody: String, param: Map<String, *>): String {
        this.param.set(param)
        return process(getTemplateFromString(templateName, templateBody))
    }


}