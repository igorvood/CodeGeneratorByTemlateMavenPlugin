package ru.vood.generator.file.resolve

import java.util.regex.Matcher
import java.util.regex.Pattern


class FileNameResolverImpl : FileNameResolver {

    override fun resolveFileByContent(typeFile: TypeFile, text: String): FilePropertyDto {
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

        if (err.isNotEmpty()) throw FileNameResolverException("$err. File text:\n$text")
        return FilePropertyDto(fileName = """${clazz!!}.${typeFile.extensionFile}""", packageStr = pack!!, type = typeFile)
    }
}