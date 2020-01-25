package ru.vood.generator.generate.runner

import ru.vood.freemarker.DataBaseFtlProcessor
import ru.vood.freemarker.FtlProcessor
import ru.vood.freemarker.ext.processor.NativeSqlFtlProcessor
import ru.vood.freemarker.ext.processor.SimpleFtlProcessor
import ru.vood.generator.GenerationException
import ru.vood.generator.generate.DataBaseConnectDto

fun resolveEngineRunner(templateEngine: TemplateEngine, dataBaseConnect: DataBaseConnectDto? = null): RunnerEngine {
    return when (templateEngine.clazz) {
        FreeMarkerEngine::class.java -> FreeMarkerEngine(FtlProcessor(SimpleFtlProcessor()))
        FreeMarkerDatabaseEngine::class.java -> {
            val dBConn = dataBaseConnect!!
            FreeMarkerDatabaseEngine(DataBaseFtlProcessor(NativeSqlFtlProcessor(dBConn.jdbcDriver, dBConn.urlDb, dBConn.userDb, dBConn.passwordDb)))
        }
        VelocityEngine::class.java -> VelocityEngine()
        ErrorEngine::class.java -> ErrorEngine()
        else -> throw GenerationException("For class ${templateEngine.clazz} do not resolve engine creation")
    }
}