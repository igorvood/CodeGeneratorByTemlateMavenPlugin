package ru.vood

import org.apache.maven.plugins.annotations.LifecyclePhase
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter
import ru.vood.generator.file.FileReaderImpl
import ru.vood.generator.file.GenerateFileImpl
import ru.vood.generator.file.getCanonicalPath
import ru.vood.generator.file.resolve.FileNameResolverImpl
import ru.vood.generator.generate.ClassGenerator
import ru.vood.generator.tjc.AbstractTjcMojo
import java.io.File

@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES, threadSafe = true)
class CodeGeneratorMojo : AbstractTjcMojo() {

    @Parameter(property = "baseDirectory", required = false, defaultValue = "\${project.build.directory}/generated-sources/tjc")
    private lateinit var baseDirectory: String

    @Parameter(property = "pluginPropertyYaml", required = true, defaultValue = "\${project.basedir}/src/main/resources/CodeGenerator.yaml")
    private lateinit var pluginPropertyYamlFile: String

    @Parameter(property = "pluginPropertyYaml", required = true, defaultValue = "\${project.basedir}/src/main/resources")
    private lateinit var templateFolder: String


    override fun execute() {
        ClassGenerator(
                FileNameResolverImpl(),
                GenerateFileImpl(),
                FileReaderImpl(), log)
                .generate(pluginPropertyYamlFile, baseDirectory, templateFolder)
        addToCompile()
    }

    private fun addToCompile() {
        val canonicalPathToOutputDirectory = getCanonicalPath(File(baseDirectory))
        // Add the output Directory.
        getProject().addCompileSourceRoot(canonicalPathToOutputDirectory)
        getProject().compileSourceRoots.forEach { log.info("compileSourceRoot -> $it") }
    }
}