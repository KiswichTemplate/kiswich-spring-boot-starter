package cn.com.scooper.autoconfigure.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@Component
@ConfigurationProperties(prefix = ProjectConfig.PREFIX)
class ProjectConfig {
    companion object {
        public const val PREFIX = "root.project"
    }

    lateinit var name: String
    lateinit var version: String

    var path = Path()

    class Path{
        lateinit var base: String
        lateinit var log: String
        lateinit var appData: String
        lateinit var config: String
    }
}

