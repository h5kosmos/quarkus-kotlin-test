package test.project.findValue.database.dto

import javax.persistence.Entity
import javax.persistence.GeneratedValue

import javax.persistence.Id

@Entity
class ValueInFiles{
    @Id
    @GeneratedValue
    var id: Long? = null
    var valueId: Long? = null
    var fileId: Long? = null
}