package cn.com.scooper.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource


@Configuration
@PropertySource(value = [
    "META-INF/MANIFEST.MF",
    "classpath:/config.properties",
    "classpath:/db.properties",
    "file:/\${user.home}\\scooper\\\${root.project.name}\\config.properties",
    "file:/\${user.home}\\scooper\\\${root.project.name}\\db.properties",
    "file:/icooper/config/\${root.project.name}/db.properties",
    "file:/icooper/config/\${root.project.name}/config.properties",
    "file:db.properties",
    "file:config.properties"], ignoreResourceNotFound = true)
open class PlatAutoConfiguration{

}
