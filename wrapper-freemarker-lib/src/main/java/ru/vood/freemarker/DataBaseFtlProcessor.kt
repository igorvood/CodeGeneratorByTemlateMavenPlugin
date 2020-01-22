package ru.vood.freemarker

import ru.vood.freemarker.ext.processor.NativeSqlFtlProcessor
import java.io.File

class DataBaseFtlProcessor(
        jdbcDriver: String,
        url: String,
        user: String,
        password: String) : TemplateProcessor {

    var nativeSqlFtlProcessor: NativeSqlFtlProcessor = NativeSqlFtlProcessor(jdbcDriver, url, user, password)

    override fun processFile(fileName: String, vararg args: Any?): String {
        val ftlText = File(fileName).readText()
        return nativeSqlFtlProcessor.process(nativeSqlFtlProcessor.getTemplateFromString(fileName, ftlText), args)
    }

    override fun registerSharedVar(name: String, `val`: Any) {
        nativeSqlFtlProcessor.setSharedVariable(name, `val`)
    }
}