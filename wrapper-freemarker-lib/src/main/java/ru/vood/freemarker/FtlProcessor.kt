package ru.vood.freemarker

import ru.vood.freemarker.ext.processor.SimpleFtlProcessor
import java.io.File

class FtlProcessor : TemplateProcessor {
    var simpleFtlProcessor: SimpleFtlProcessor = SimpleFtlProcessor()

    override fun processFile(fileName: String, vararg args: Any?): String {
        val ftlText = File(fileName).readText()
        simpleFtlProcessor.registerSharedVar("args", args)
        return simpleFtlProcessor.process(simpleFtlProcessor.getTemplateFromString(fileName, ftlText), args)
    }

    override fun registerSharedVar(name: String, `val`: Any) {
        simpleFtlProcessor.setSharedVariable(name, `val`)
    }

}