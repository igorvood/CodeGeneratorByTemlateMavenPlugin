package ru.vood.freemarker.ext.processor

import freemarker.template.Template
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments
import java.io.Writer
import java.util.function.Consumer
import java.util.stream.Stream

internal open class AbstractFtlProcessorTest {

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

    @AfterEach
    fun afterTests() {
        unmockkAll()
        // or unmockkObject(MockObj)
    }

    //    @JvmStatic
    open fun testData(): Stream<Arguments> {
        return Stream.of(
                Arguments.of("test_template_name", Consumer<Any> { abstractFtlProcessorTest.process("") }),
                Arguments.of("collection_from_query", Consumer<Any> { abstractFtlProcessorTest.process("") }),
                Arguments.of("clob_from_query", Consumer<Any> { abstractFtlProcessorTest.process("") }),
                Arguments.of("struct_from_query", Consumer<Any> { abstractFtlProcessorTest.process("") })
        )
    }

    val data = Stream.of(
            Pair("test_template_name", Consumer<Any> { abstractFtlProcessorTest.process("") }),
            Pair("collection_from_query", Consumer<Any> { abstractFtlProcessorTest.process("") })
    )

    @Test
    fun process_1() {
        testRunner(Consumer<Any> { abstractFtlProcessorTest.process(template) })
    }

    @Test
    fun process_2() {
        testRunner(Consumer<Any> { abstractFtlProcessorTest.process("templateName") })
    }

    @Test
    fun process_3() {
        testRunner(Consumer<Any> { abstractFtlProcessorTest.process(this::class.java, "templateName") })
    }

    @Test
    fun process_4() {
        testRunner(Consumer<Any> { abstractFtlProcessorTest.process("templateName", "templateBody") })
    }

    @Test
    fun process_5() {
        testRunner(Consumer<Any> { abstractFtlProcessorTest.process("templateName", mapOf()) })
    }

    @Test
    fun process_6() {
        testRunner(Consumer<Any> { abstractFtlProcessorTest.process(this::class.java, "templateName", mapOf()) })
    }

    @Test
    fun process_7() {
        testRunner(Consumer<Any> { abstractFtlProcessorTest.process("templateName", "", mapOf()) })
    }

/*

    fun process(templateName: String, templateBody: String, param: Map<String, Any>): String {
        param.forEach { registerSharedVar(it.key, it.value) }
        return process(getTemplateFromString(templateName, templateBody))
    }
*/


    private fun testRunner(cons: Consumer<Any>) {
        every { abstractFtlProcessorTest.getTemplate(any()) } returns template
        every { abstractFtlProcessorTest.process(template, writer) } answers { nothing }
        cons.accept("")
        //        abstractFtlProcessorTest.process("")
        verify(atLeast = 1, atMost = 1) { abstractFtlProcessorTest.process(allAny<Template>(), allAny<Writer>(), *anyVararg<Any>()) }
        unmockkAll()
    }

/*    @Test
    fun getTemplate() {
    }

    @Test
    fun testGetTemplate() {
    }

    @Test
    fun getTemplateFromString() {
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
    }*/

    open class TestAbstraction : AbstractFtlProcessor()
}