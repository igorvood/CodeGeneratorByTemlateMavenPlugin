package ru.vood.freemarker.ext.processor

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import sfqtl.TestConfig
import java.io.File
import java.nio.file.Files
import java.util.stream.Stream
import javax.sql.DataSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = [TestConfig::class])
@ExtendWith(SpringExtension::class)
internal class NativeSqlFtlProcessorTest {

    @Autowired
    lateinit var dbConnectionUrl: String

    @Autowired
    lateinit var dbConnectionUser: String

    @Autowired
    lateinit var dbConnectionPassword: String

    @Autowired
    lateinit var dataSource: DataSource

    lateinit var nativeSqlFtlProcessor: NativeSqlFtlProcessor

    @BeforeEach
    private fun setup() {
        nativeSqlFtlProcessor = NativeSqlFtlProcessor("oracle.jdbc.driver.OracleDriver", dbConnectionUrl, dbConnectionUser, dbConnectionPassword)
    }


    @ParameterizedTest
    @MethodSource("testData")
    fun process(fileName: String) {
        val process = nativeSqlFtlProcessor.process("sfqtl/$fileName.ftl")
        Assertions.assertEquals(loadListOfStrings(fileName).trim(), process.trim())
    }

    private fun loadListOfStrings(fileName: String): String {
        val templateName = "sfqtl/$fileName.txt"
        var file = File(templateName)
        if (!file.exists()) {
            val resource = javaClass.classLoader.getResource(templateName)
                    ?: throw NoSuchFileException(file, null, "File $templateName not exists")
            file = File(resource.toURI())
            if (!file.exists())
                throw NoSuchFileException(file, null, "File $templateName not exists")
        }

        return Files.readString(file.toPath())
    }


    private fun testData(): Stream<Arguments> {
        return Stream.of(
                "test_template_name",
                "collection_from_query",
                "clob_from_query",
                "struct_from_query",
                "sql_types_query",
                "sql_types_call",
                "rs_transponse",
                "test_static"
        )
                .map { arguments: String -> Arguments.of(arguments) }
    }
}