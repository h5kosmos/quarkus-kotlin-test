package test.project.findValue.database.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import test.project.findValue.database.dto.ValueInFiles
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
class ValueInFilesRepository: PanacheRepository<ValueInFiles> {
    fun deleteByFileId(fileId: Long) = delete("fileId", fileId)
    fun findByValueId(valueId: Long) = find("valueId",valueId)
}