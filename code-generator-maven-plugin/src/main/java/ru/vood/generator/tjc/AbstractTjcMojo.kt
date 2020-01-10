package ru.vood.generator.tjc

import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugins.annotations.Parameter
import org.apache.maven.project.MavenProject

abstract class AbstractTjcMojo : AbstractMojo() {

    @Parameter(defaultValue = "\${project}", readonly = true)
    private val project: MavenProject? = null


    protected fun getProject(): MavenProject {
        return getInjectedObject<MavenProject>(project, "project")
    }

    private fun <T> getInjectedObject(objectOrNull: T?, objectName: String): T {
        if (objectOrNull == null) {
            log.error("Found null '$objectName', implying that Maven @Component injection was not done properly.")
        }
        return objectOrNull!!
    }

}