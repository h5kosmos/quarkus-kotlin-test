package test.project.findValue.database.dto


import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class FileEntity {
    @Id
    @GeneratedValue
    var id: Long? = null
    var name: String = ""
}