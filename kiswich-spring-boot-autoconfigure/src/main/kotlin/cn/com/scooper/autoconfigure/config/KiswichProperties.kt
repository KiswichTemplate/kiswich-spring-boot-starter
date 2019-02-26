package cn.com.scooper.autoconfigure.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.system.SystemProperties

@ConfigurationProperties(prefix = KiswichProperties.PREFIX)
open class KiswichProperties {
    companion object {
        const val PREFIX: String = "scooper.default"
    }


    val path = Path()
    val cache: Cache = Cache()
    val retrofit: Retrofit = Retrofit()
    val executor: Executor = Executor()
    val swagger: Swagger = Swagger()

    open class Path {
        var isLinux: Boolean = (SystemProperties.get("os.name").toLowerCase().contains("linux"))
    }

    open class Cache {
        var enable: Boolean = true
        var spec: String = "maximumSize=500,expireAfterAccess=600s"
    }

    open class Retrofit {
        var enable: Boolean = true
        var dateFormat: String = "yyyy-MM-dd HH:mm:ss"
    }


    open class Executor {
        var enable: Boolean = true
        var corePoolSize: Int = 50
        var maxPoolSize: Int = 100
        var keepAliveSeconds: Int = 60
    }

    open class Swagger {
        var enable: Boolean = true

    }
}
