package ru.vood.generator.generate.runner

import ru.vood.freemarker.DataBaseFtlProcessor
import ru.vood.freemarker.FtlProcessor
import ru.vood.generator.GenerationException

fun resolveEngineRunner(templateEngine: TemplateEngine): RunnerEngine {
    return when (templateEngine.clazz) {
        FreeMarkerEngine::class.java -> FreeMarkerEngine(FtlProcessor())
        FreeMarkerDatabaseEngine::class.java -> FreeMarkerDatabaseEngine(DataBaseFtlProcessor("===TODO===", "", "", ""))
        VelocityEngine::class.java -> VelocityEngine()
        ErrorEngine::class.java -> ErrorEngine()
        else -> throw GenerationException("For class ${templateEngine.clazz} do not resolve engine creation")
    }
}