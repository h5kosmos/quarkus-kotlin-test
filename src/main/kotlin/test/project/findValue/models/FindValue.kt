package test.project.findValue.models

import Config
import test.project.findValue.database.dto.FileEntity
import test.project.findValue.database.dto.Value
import test.project.findValue.database.dto.ValueInFiles
import test.project.findValue.database.repository.FileRepository
import test.project.findValue.database.repository.ValueInFilesRepository
import test.project.findValue.database.repository.ValueRepository
import java.io.File
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class FindValue (@Inject private val config: Config) {

    @Inject
    lateinit var fileRepository: FileRepository

    @Inject
    lateinit var valueRepository: ValueRepository

    @Inject
    lateinit var valueInFilesRepository: ValueInFilesRepository


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

    fun getAllFilesNames(): MutableSet<String> {
        val names: MutableSet<String> = mutableSetOf()
        val dir = getAllFiles()
        dir.forEach { f ->
            names.add(f.name)
        }
        return names
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
    fun parseFileToDb(fileName: String){
        val currentValues = getAllValuesInFile(File(config.path +"/" + fileName))
        val file= FileEntity()
        file.name = fileName
        fileRepository.persist(file)
        currentValues.forEach {
            val value = Value()
            value.value = it
            valueRepository.persist(value)
            val valueInFile = ValueInFiles()
            valueInFile.fileId = file.id
            valueInFile.valueId = value.id
            valueInFilesRepository.persist(valueInFile)
        }
    }

    fun findValueInFiles(value: String): MutableSet<String> {
        return fileRepository.findByValue(value)
    }

    fun getAllValuesInFile(file: File): List<String> {
        return file.readText().split("${config.delimiter}".toRegex())
    }
}