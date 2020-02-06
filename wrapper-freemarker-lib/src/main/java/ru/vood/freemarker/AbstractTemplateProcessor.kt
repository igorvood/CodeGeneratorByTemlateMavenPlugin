package ru.vood.freemarker

import ru.vood.freemarker.ext.processor.ProcessFtl

abstract class AbstractTemplateProcessor(val processFtl: ProcessFtl) : TemplateProcessor {

    override fun process(fileName: String, vararg args: Any?): String = processFtl.process(fileName, *args)

    override fun process(clazz: Class<*>, fileName: String, vararg args: Any?): String = processFtl.process(clazz, fileName, *args)

    override fun process(templateName: String, templateBody: String, vararg args: Any?): String {
        return processFtl.process(templateName, templateBody, *args)
    }

    override fun process(templateName: String, param: Map<String, *>): String {
        return processFtl.process(templateName, param)
    }

    override fun process(clazz: Class<*>, templateName: String, param: Map<String, *>): String {
        return processFtl.process(clazz, templateName, param)
    }

    override fun process(templateName: String, templateBody: String, param: Map<String, *>): String {
        return processFtl.process(templateName, templateBody, param)
    }



}