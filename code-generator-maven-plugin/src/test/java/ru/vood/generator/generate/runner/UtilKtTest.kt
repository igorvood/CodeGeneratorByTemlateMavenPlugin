package ru.vood.generator.generate.runner

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class UtilKtTest {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun resolveEngineRunner() {
        TemplateEngine.values().asList()
                .stream()
                .map { Pair(it, resolveEngineRunner(it)) }
                .forEach { Assertions.assertNotNull(it.second, "Engine runner for ${it.first} do not resolve") }
    }
}