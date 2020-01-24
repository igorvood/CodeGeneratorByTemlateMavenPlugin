package ru.vood.freemarker.ext.processor

import freemarker.template.Template
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.Writer

open class AbstractFtlProcessorTest {

    private lateinit var abstractFtlProcessorTest: AbstractFtlProcessor

    @MockK(relaxed = true)
    lateinit var template: Template

    @MockK(relaxed = true)
    lateinit var writer: Writer

    init {
        MockKAnnotations.init(this)
    }

    @BeforeEach
    open fun setUp() {
        abstractFtlProcessorTest = spyk(TestAbstraction())

    }

    @Test
    fun getTemplate() {
    }

    @Test
    fun testGetTemplate() {
    }

    @Test
    fun getTemplateFromString() {
    }

    @Test
    fun process() {
        every { abstractFtlProcessorTest.getTemplate(any()) } returns template
        every { abstractFtlProcessorTest.process(template, writer) } answers { nothing }

        abstractFtlProcessorTest.process("")
        verify(atLeast = 1, atMost = 1) { abstractFtlProcessorTest.process(allAny<Template>(), allAny<Writer>(), *anyVararg<Any>()) }


//        confirmVerified(abstractFtlProcessorTest)

//        verify(abstractFtlProcessorTest, times(1)).process(template)
//        abstractFtlProcessorTest.process("1")
//        verify(abstractFtlProcessorTest, times(1)).process(template)

    }

    @Test
    fun testProcess() {
    }

    @Test
    fun registerSharedVar() {
    }

    @Test
    fun getFtlDefaultObjectWrapper() {
    }

    @Test
    fun getGetStaticMethod() {
    }

    open class TestAbstraction : AbstractFtlProcessor()
}