package ru.vood.freemarker

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import ru.vood.freemarker.ext.processor.SimpleFtlProcessor
import java.util.*


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@ContextConfiguration(classes = [TestConfig::class])
@ExtendWith(MockKExtension::class)
internal class FtlProcessorTest {

    private lateinit var ftlProcessor: TemplateProcessor

    @BeforeEach
    fun setUp() {
        ftlProcessor = FtlProcessor(SimpleFtlProcessor())
    }

    @Test
    fun processFileNoParam() {
        val processFile = ftlProcessor.process(fileName = "src/test/resources/ru/vood/freemarker/FtlProcessorTest/FtlProcessorImplTestNoParam.ftl")
        Assertions.assertEquals("--NO PARAM--", processFile)
    }

    @Test
    fun processFileByClassNoParam() {
        val processFile = ftlProcessor.process(FtlProcessorTest::class.java, "FtlProcessorImplTestNoParam.ftl")
        Assertions.assertEquals("--NO PARAM--", processFile)
    }

    @Test
    fun processFileByClassWithParam() {
        val stringParam = "zxc"
        val processFile = ftlProcessor.process(FtlProcessorTest::class.java, "FtlProcessorImplTestWithParam.ftl", stringParam)
        Assertions.assertEquals("PARAM->$stringParam", processFile)
    }

    @Test
    fun processFileByClassWithParamObject_1() {
        val car = Car("BMW", 1234, Date())
        val car1 = Car("VW", 987, Date())
        val processFile = ftlProcessor.process(FtlProcessorTest::class.java, "FtlProcessorImplTestWithParam.ftl", car, car1)
        Assertions.assertEquals("PARAM->$car", processFile)
    }

    @Test
    fun processFileByClassWithParamObject_2() {
        val stringParam = Car("BMW", 123456789, Date(100, 3, 13))
        val processFile = ftlProcessor.process(FtlProcessorTest::class.java, "FtlProcessorImplTestWithParamObject.ftl", stringParam)
        Assertions.assertTrue(processFile.contains("model->BMW,price->123456789,date->"))
        Assertions.assertTrue(processFile.contains(",dateTime->"))
        Assertions.assertEquals("model->BMW,price->123456789,date->2000-04-13,dateTime->2000-04-13 00:00:00", processFile)
    }

    @Test
    fun processFileByClassWithParamObject_3() {
        val stringParam = Car("BMW", 123456789, Date(100, 3, 13))
        val mapOf = mapOf<String, Any>("carJava" to stringParam)
        val processFile = ftlProcessor.process(FtlProcessorTest::class.java, "FtlProcessorImplTestWithParamObject2.ftl", mapOf)
        Assertions.assertTrue(processFile.contains("model->BMW,price->123456789,date->"))
        Assertions.assertTrue(processFile.contains(",dateTime->"))
        Assertions.assertEquals("model->BMW,price->123456789,date->2000-04-13,dateTime->2000-04-13 00:00:00", processFile)
    }

    @Test
    fun processNotExistsFile() {
        Assertions.assertThrows(NoSuchFileException::class.java) { ftlProcessor.process(FtlProcessorTest::class.java, "qwerty.ftl") }
    }
}