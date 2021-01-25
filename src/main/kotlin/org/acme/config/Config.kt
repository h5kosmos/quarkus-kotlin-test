import io.quarkus.arc.config.ConfigProperties

@ConfigProperties(prefix = "findValue")
class Config {
    lateinit var path: String
}