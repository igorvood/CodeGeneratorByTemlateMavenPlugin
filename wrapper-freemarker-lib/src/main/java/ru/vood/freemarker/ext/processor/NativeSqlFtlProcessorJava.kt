package ru.vood.freemarker.ext.processor

@Deprecated("delete")
class NativeSqlFtlProcessorJava(jdbcDriver: String, url: String, username: String, password: String) : SimpleFtlProcessor() {
    private val jdbcDriver: String
    private val url: String
    private val username: String
    private val password: String

    init {
        try {
            Class.forName(jdbcDriver)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        this.jdbcDriver = jdbcDriver
        this.url = url
        this.username = username
        this.password = password
    }
}