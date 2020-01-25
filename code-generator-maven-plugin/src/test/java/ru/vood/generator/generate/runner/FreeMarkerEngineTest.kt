package ru.vood.generator.generate.runner

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.vood.freemarker.FtlProcessor
import ru.vood.freemarker.ext.processor.SimpleFtlProcessor
import ru.vood.generator.read.dto.yamlParamTemplateDtoObject
import java.io.File

internal class FreeMarkerEngineTest {

    private lateinit var freeMarkerEngine: FreeMarkerEngine

    @BeforeEach
    fun setUp() {
        freeMarkerEngine = FreeMarkerEngine(FtlProcessor(SimpleFtlProcessor()))
    }

    @Test
    fun generateText() {
        val generateText = freeMarkerEngine.generateText(
                yamlParamTemplateDtoObject(),
                File("src/test/resources/ru/vood/generator/generate/runner/templateFile1.ftl")
        ).trim()
        val expected = File("src/test/resources/ru/vood/generator/generate/runner/templateFile1.txt")
                .readText().trim()
        assertEquals(expected, generateText)
    }
}