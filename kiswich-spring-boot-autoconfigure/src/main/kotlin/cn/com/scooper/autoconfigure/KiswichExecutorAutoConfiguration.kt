package cn.com.scooper.autoconfigure

import cn.com.scooper.autoconfigure.config.KiswichProperties
import cn.com.scooper.autoconfigure.config.PlatProperties
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.support.ExecutorServiceAdapter
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
@ConditionalOnProperty(name = ["scooper.default.executor.enable"], havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(KiswichProperties::class, PlatProperties::class)
open class KiswichExecutorAutoConfiguration {
    @Bean
    @ConditionalOnProperty(name = ["scooper.default.executor.enable"], havingValue = "true", matchIfMissing = true)
    @ConfigurationProperties(prefix = "root.thread-pool-task")
    open fun commonFixedExecutorService(): ThreadPoolTaskExecutor {
        return ThreadPoolTaskExecutor()
    }

    @Bean
    @ConditionalOnProperty(name = ["scooper.default.executor.enable"], havingValue = "true", matchIfMissing = true)
    open fun executorServiceAdapter(): ExecutorServiceAdapter {
        return ExecutorServiceAdapter(commonFixedExecutorService())
    }

}
