package ru.vood.freemarker.util

class UtilMethodsForTemplateUse() {

    companion object {
        @JvmStatic
        fun toCamelCase(s: String): String {
            val ret = StringBuilder(s.length)
            for (word in s.split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
                if (word.isNotEmpty()) {
                    ret.append(word.substring(0, 1).toUpperCase())
                    ret.append(word.substring(1).toLowerCase())
                }
            }
            return ret.toString()
        }

        @JvmStatic
        fun toCamelCaseFirstLetterLower(s: String): String {
            val toCamelCase = toCamelCase(s)
            return toCamelCase.substring(0, 1).toLowerCase() + toCamelCase.substring(1)
        }

        @JvmStatic
        fun sqlToJavaTypeMapping(sqlType: String): String {
            val cl = TypeMapping.values()
                    .filter { sqlType.contains(it.sqlType) }
                    .map { it.javaClazz }
                    .firstOrNull()
            if (cl != null) {
                return /*cl.simpleName!!+"->"+cl.jvmName+"->"+*/cl.qualifiedName!!
            } else throw IllegalStateException("No compatible type $sqlType")

        }

        @JvmStatic
        fun sqlToRsMethodMapping(sqlType: String): String {
            val cl = TypeMapping.values()
                    .filter { sqlType.contains(it.sqlType) }
                    .map { it.getFromRs }
                    .firstOrNull()
            if (cl != null) {
                return cl
            } else throw IllegalStateException("No compatible type $sqlType")

        }

    }

}