package ru.vood.generator.file.resolve

import java.util.regex.Matcher
import java.util.regex.Pattern


class FileNameResolverImpl : FileNameResolver {

    private val s = "-------------------------------------------------------------"
    override fun resolveFileByContent(typeFile: TypeFile, text: String, templateFile: String, paramFile: String): FilePropertyDto {

        val patternPack: Pattern = Pattern.compile(typeFile.packageRegexp, Pattern.MULTILINE)
        val matcherPack: Matcher = patternPack.matcher(text)
        var err = ""
        var pack: String? = null
        if (matcherPack.find()) {
            pack = matcherPack.group(1)
        } else err = "Can not resolve package name, regexp=${typeFile.packageRegexp}."
        val patternClass: Pattern = Pattern.compile(typeFile.classNameRegexp, Pattern.MULTILINE)
        val matcherClass: Matcher = patternClass.matcher(text)
        var clazz: String? = null

        if (matcherClass.find()) {
            clazz = matcherClass.group(6)
        } else err += " Can not resolve class name, regexp=${typeFile.classNameRegexp}."

        if (err.isNotEmpty()) throw FileNameResolverException("$err\ntemplateFile:$templateFile\nparamFile:$paramFile\n$s\nGenerated text text:\n$text\n$s")
        return FilePropertyDto(fileName = """${clazz!!}.${typeFile.extensionFile}""", packageStr = pack!!, type = typeFile)
    }
}