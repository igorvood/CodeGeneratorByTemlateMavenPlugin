package ru.vood.generator.file

import java.io.File
import java.io.IOException

/**
 * Acquires the canonical path for the supplied file.
 *
 * @param file A non-null File for which the canonical path should be retrieved.
 * @return The canonical path of the supplied file.
 */
fun getCanonicalPath(file: File): String {
    return getCanonicalFile(file).path
}

/**
 * Acquires the canonical File for the supplied file.
 *
 * @param file A non-null File for which the canonical File should be retrieved.
 * @return The canonical File of the supplied file.
 */
fun getCanonicalFile(file: File): File {
    // All done
    return try {
        file.canonicalFile
    } catch (e: IOException) {
        throw IllegalArgumentException("Could not acquire the canonical file for ["
                + file.absolutePath + "]", e)
    }
}

//class FileSystemUtilities {
//
//
//}