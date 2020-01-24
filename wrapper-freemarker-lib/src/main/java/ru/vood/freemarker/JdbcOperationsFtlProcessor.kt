package ru.vood.freemarker

import ru.vood.freemarker.ext.processor.SpringFtlProcessor

class JdbcOperationsFtlProcessor(val sqlFtlProcessor: SpringFtlProcessor) : AbstractTemplateProcessor(sqlFtlProcessor) {

    override fun process(fileName: String, vararg args: Any?): String {
        return sqlFtlProcessor.process(fileName, args)
    }

    override fun registerSharedVar(name: String, `val`: Any) {
        sqlFtlProcessor.setSharedVariable(name, `val`)
    }

}