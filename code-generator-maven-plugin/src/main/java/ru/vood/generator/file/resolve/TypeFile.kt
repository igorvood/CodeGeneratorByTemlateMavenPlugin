package ru.vood.generator.file.resolve

import ru.vood.generator.file.resolve.TypeFile.Const.JAVA_CLASS_NAME
import ru.vood.generator.file.resolve.TypeFile.Const.KOTLIN_CLASS_NAME
import ru.vood.generator.file.resolve.TypeFile.Const.PACK

enum class TypeFile(val extensionFile: String
                    , val classNameRegexp: String
                    , val packageRegexp: String) {

    JAVA("java", JAVA_CLASS_NAME, PACK),
    KOTLIN("kt", KOTLIN_CLASS_NAME, PACK);

    protected object Const {
        const val PACK: String = "package\\s{1,}((\\w{1,}\\.{0,}){1,})"

        const val JAVA_CLASS_NAME: String = "((interface)|(class)|(enum)|(object))\\s{1,}((\\w{1,}){1})"


        private const val KOTLIN_CLASS_NAME_1: String = "((interface).[{])|((class).[{])|((object).[{])"
        private const val KOTLIN_CLASS_NAME_2: String = "((interface).[(])|((class).[(])|((object).[(])"

        const val KOTLIN_CLASS_NAME: String = "((interface)|(class)|(object))\\s{1,}((\\w{1,}){1})"
    }


}
