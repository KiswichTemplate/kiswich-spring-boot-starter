package cn.com.scooper.autoconfigure.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = PlatProperties.PREFIX)
open class PlatProperties {
    companion object {
        final const val PREFIX = "scooper.plat"
    }

    var scooperCoreUrl: String = "http://127.0.0.1:8080/scooper-core-rest/"
    var mapServerUrl:String = "http://127.0.0.1:8080/scgis/"
    var commonGisUrl:String = "http://127.0.0.1:8080/common-gis-web/"
    var scooperVideoUrl:String = "http://127.0.0.1:8080/scooper-video/"
}
