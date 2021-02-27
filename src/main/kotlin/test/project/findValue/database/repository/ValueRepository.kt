package test.project.findValue.database.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import test.project.findValue.database.dto.Value
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@Transactional
@ApplicationScoped
class ValueRepository : PanacheRepository<Value> {
    fun findByValue(name: String) = find("value", name).firstResult()
    fun deleteValue(id: Long) = delete("id", id)
}