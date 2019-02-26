package cn.com.scooper.autoconfigure

import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@AutoConfigureAfter(KiswichRetrofitAutoConfiguration::class)
@ComponentScan("cn.com.scooper.autoconfigure.aftercomponent")
open class AfterComponentScan
