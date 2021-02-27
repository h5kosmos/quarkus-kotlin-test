package test.project.findValue.database.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import test.project.findValue.database.dto.FileEntity
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
class FileRepository : PanacheRepository<FileEntity> {
    fun findByName(name: String) = find("name", name).firstResult()
    fun deleteFile() = delete("name", "Stef")
    fun findByValue(valueId: String): MutableSet<String> {
        val fileNames: MutableSet<String> = mutableSetOf()
        find("from File as f, Value as v, ValueInFiles as fiv" +
                "where f.id=fiv.fileId and v.id=fiv.valueId and v.value= ?1", valueId).list().forEach{
            fileNames.add(it.name)
        }
        return fileNames
    }
}