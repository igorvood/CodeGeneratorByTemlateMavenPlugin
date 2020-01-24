package ru.vood.freemarker.ext.processor

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.vood.freemarker.Car
import ru.vood.freemarker.FtlProcessorTest
import java.util.*

internal class SimpleFtlProcessorTest {
    private lateinit var ftlProcessor: ProcessFtl
    @BeforeEach
    fun setUp() {
        ftlProcessor = SimpleFtlProcessor()
    }

    @Test
    fun processFileNoParam() {
        val template = ftlProcessor.getTemplate("ru/vood/freemarker/FtlProcessorTest/FtlProcessorImplTestNoParam.ftl")
        val processFile = ftlProcessor.process(template)
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
        val template = ftlProcessor.getTemplate(FtlProcessorTest::class.java, "FtlProcessorImplTestWithParam.ftl")
        val car = Car("BMW", 1234, Date())
        val car1 = Car("VW", 987, Date())
        val processFile = ftlProcessor.process(FtlProcessorTest::class.java, "FtlProcessorImplTestWithParam.ftl", car, car1)
        Assertions.assertEquals("PARAM->$car", processFile)
    }

    @Test
    fun processFileByClassWithParamObject_2() {
        val template = ftlProcessor.getTemplate(FtlProcessorTest::class.java, "FtlProcessorImplTestWithParamObject.ftl")
        val stringParam = Car("BMW", 123456789, Date(100, 3, 13))
        val processFile = ftlProcessor.process(template, stringParam)
        Assertions.assertTrue(processFile.contains("model->BMW,price->123456789,date->"))
        Assertions.assertTrue(processFile.contains(",dateTime->"))
        Assertions.assertEquals("model->BMW,price->123456789,date->2000-04-13,dateTime->2000-04-13 00:00:00", processFile)
    }

    @Test
    fun processFileByClassWithParamObject_3() {
        val template = ftlProcessor.getTemplate(FtlProcessorTest::class.java, "FtlProcessorImplTestWithParamObject2.ftl")
        val stringParam = Car("BMW", 123456789, Date(100, 3, 13))
        ftlProcessor.registerSharedVar("carJava", stringParam)
        val processFile = ftlProcessor.process(template)
        Assertions.assertTrue(processFile.contains("model->BMW,price->123456789,date->"))
        Assertions.assertTrue(processFile.contains(",dateTime->"))
        Assertions.assertEquals("model->BMW,price->123456789,date->2000-04-13,dateTime->2000-04-13 00:00:00", processFile)
    }

    @Test
    fun processNotExistsFile() {
        Assertions.assertThrows(NoSuchFileException::class.java) { ftlProcessor.getTemplate(FtlProcessorTest::class.java, "qwerty.ftl") }
    }
}