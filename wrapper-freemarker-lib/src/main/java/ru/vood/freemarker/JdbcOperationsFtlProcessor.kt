package ru.vood.freemarker

import org.springframework.jdbc.core.JdbcTemplate
import ru.vood.freemarker.ext.processor.SpringFtlProcessor
import java.io.File

class JdbcOperationsFtlProcessor(jdbcOperations: JdbcTemplate) : TemplateProcessor {

    private val sqlFtlProcessor: SpringFtlProcessor = SpringFtlProcessor(jdbcOperations)

    override fun processFile(fileName: String, vararg args: Any?): String {
        val ftlText = File(fileName).readText()
        return sqlFtlProcessor.process(sqlFtlProcessor.getTemplateFromString(fileName, ftlText), args)
    }

    override fun registerSharedVar(name: String, `val`: Any) {
        sqlFtlProcessor.setSharedVariable(name, `val`)
    }

}