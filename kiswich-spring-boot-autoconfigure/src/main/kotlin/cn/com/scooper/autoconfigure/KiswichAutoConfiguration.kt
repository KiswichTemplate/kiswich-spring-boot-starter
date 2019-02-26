package cn.com.scooper.autoconfigure

import cn.com.scooper.autoconfigure.config.KiswichProperties
import cn.com.scooper.autoconfigure.config.PlatProperties
import cn.com.scooper.autoconfigure.config.ProjectConfig
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@EnableConfigurationProperties(KiswichProperties::class, PlatProperties::class, ProjectConfig::class)
@AutoConfigureAfter(DataSourceAutoConfiguration::class, ComponentScan::class)
open class KiswichAutoConfiguration(
        val kiswichProperties: KiswichProperties,
        val platProperties: PlatProperties,
        val projectConfig: ProjectConfig
) {


}
