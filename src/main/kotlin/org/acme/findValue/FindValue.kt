package org.acme.findValue

import org.acme.config.Config
import java.io.File
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class FindValue (@Inject private val config: Config) {

    fun getAllFiles(): MutableSet<File> {
        val result: MutableSet<File> = mutableSetOf()
        val dir = File(config.path)
        dir.walk().forEach { f ->
            if(f.isFile) {
                result.add(f)
            }
        }
        return result
    }

    fun findValueInFile(file:File, value:String): Boolean {
        val regex: Regex = "\\b$value\\b".toRegex()
        return file.readText().contains(regex)
    }

    fun findFilesWithValue(value:String): MutableSet<String> {
        val files: Set<File> = getAllFiles()
        val result = mutableSetOf<String>()
        files.forEach {
            if (findValueInFile(it,value)) {
                result.add(it.name)
            }
        }
        return result
    }
}