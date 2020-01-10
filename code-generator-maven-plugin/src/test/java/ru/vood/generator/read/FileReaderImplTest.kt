package ru.vood.generator.read

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.vood.generator.file.FileReader
import ru.vood.generator.file.FileReaderImpl
import kotlin.test.assertTrue

internal class FileReaderImplTest {

    private lateinit var fileReaderImpl: FileReader

    @BeforeEach
    fun setUp() {
        fileReaderImpl = FileReaderImpl()
    }

    @Test
    fun readFile() {
        val readFile = fileReaderImpl.readFile("XMLValidatorImplTest/test.error.xml")
        assertNotNull(readFile)
        assertTrue(readFile.contains("""<pluginTines username="string" password="string" url="string">"""))
    }

    @Test
    fun readFileNotFound() {
        try {
            fileReaderImpl.readFile("asdf")
        } catch (e: Throwable) {
            assertTrue(e.message!!.contains(" is not found!"))
//            assertEquals("file asdf is not found!", e.message)
        }

    }

}