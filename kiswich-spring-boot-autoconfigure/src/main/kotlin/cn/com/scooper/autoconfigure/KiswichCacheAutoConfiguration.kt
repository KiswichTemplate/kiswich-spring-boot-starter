package cn.com.scooper.autoconfigure

import cn.com.scooper.autoconfigure.config.KiswichProperties
import cn.com.scooper.autoconfigure.config.PlatProperties
import com.github.benmanes.caffeine.cache.CaffeineSpec
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration::class, cn.com.scooper.autoconfigure.ComponentScan::class)
@EnableConfigurationProperties(KiswichProperties::class,PlatProperties::class)
@ConditionalOnProperty(name = ["scooper.default.cache.enable"], havingValue = "true",matchIfMissing = true)
@EnableCaching
@ComponentScan("cn.com.scooper.autoconfigure.componet")
open class KiswichCacheAutoConfiguration(
        val kiswichProperties: KiswichProperties
) {
    @Bean
    @ConditionalOnMissingBean
    open fun caffeineSpec(): CaffeineSpec {
        return CaffeineSpec.parse(kiswichProperties.cache.spec)
    }
}
