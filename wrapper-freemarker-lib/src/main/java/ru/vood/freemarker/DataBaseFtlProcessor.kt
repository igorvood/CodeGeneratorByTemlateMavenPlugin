package ru.vood.freemarker

import ru.vood.freemarker.ext.processor.NativeSqlFtlProcessor

class DataBaseFtlProcessor(val nativeSqlFtlProcessor: NativeSqlFtlProcessor) : AbstractTemplateProcessor(nativeSqlFtlProcessor) {

    override fun process(fileName: String, vararg args: Any?): String {
        return nativeSqlFtlProcessor.process(fileName, args)
    }

    override fun registerSharedVar(name: String, `val`: Any) {
        nativeSqlFtlProcessor.setSharedVariable(name, `val`)
    }
}