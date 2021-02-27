package test.project.findValue.database.dto

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Value {
    @Id
    @GeneratedValue
    var id: Long? = null
    var value: String = ""
}