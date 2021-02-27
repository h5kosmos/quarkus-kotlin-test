package test.project


import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test
import test.project.findValue.database.dto.Value
import test.project.findValue.database.repository.ValueRepository
import test.project.findValue.models.FindValue
import javax.inject.Inject

@QuarkusTest
class GreetingResourceTest {

    @Inject
    lateinit var findValue: FindValue

    @Inject
    lateinit var valueRepository: ValueRepository

    @Test
    fun testHelloEndpoint() {
        for (i in 0..1000) {
            val value= Value()
            value.value = i.toString()
            valueRepository.persist(value)
        }
        valueRepository.flush()
    }
}