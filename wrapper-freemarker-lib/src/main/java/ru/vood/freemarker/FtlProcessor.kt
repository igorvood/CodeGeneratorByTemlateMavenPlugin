package ru.vood.freemarker

import ru.vood.freemarker.ext.processor.SimpleFtlProcessor

class FtlProcessor : TemplateProcessor {
    var simpleFtlProcessor: SimpleFtlProcessor = SimpleFtlProcessor()

    override fun processFile(fileName: String, vararg args: Any?): String {
        simpleFtlProcessor.registerSharedVar("args", args)
        println("FtlProcessor ")
        return simpleFtlProcessor.process(simpleFtlProcessor.getTemplate(fileName), args)
    }

    override fun registerSharedVar(name: String, `val`: Any) {
        simpleFtlProcessor.setSharedVariable(name, `val`)
    }

}