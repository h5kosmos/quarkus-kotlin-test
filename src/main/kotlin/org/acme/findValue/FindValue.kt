package org.acme.findValue

import Config
import java.io.File
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class FindValue(@Inject private val config: Config) {

    val regex: Regex = "\\b${config.delimiter}\\b".toRegex()

    fun getAllFiles(): MutableSet<File> {
        val result: MutableSet<File> = mutableSetOf()
        val dir = File(config.path)
        dir.walk()
        dir.walk().forEach { f ->
            if (f.isFile) {
                result.add(f)
            }
        }
        return result
    }

    fun findValueInFile(file: File, value: String): Boolean {
        return file.readText().contains(regex)
    }

    fun findFilesWithValue(value: String): MutableSet<String> {
        val files: Set<File> = getAllFiles()
        val result = mutableSetOf<String>()
        files.forEach {
            if (findValueInFile(it, value)) {
                result.add(it.name)
            }
        }
        return result
    }
}