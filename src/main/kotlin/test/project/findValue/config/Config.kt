import io.quarkus.arc.config.ConfigProperties

@ConfigProperties(prefix = "findValue")
class Config {
    lateinit var delimiter: String
    lateinit var path: String
}