package cn.com.scooper.autoconfigure

import cn.com.scooper.autoconfigure.componet.SimpleExecutorService
import cn.com.scooper.autoconfigure.componet.TokenInterceptor
import cn.com.scooper.autoconfigure.config.KiswichProperties
import cn.com.scooper.autoconfigure.config.PlatProperties
import cn.com.scooper.autoconfigure.service.ScooperCoreAccountService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration::class, cn.com.scooper.autoconfigure.ComponentScan::class)
@EnableConfigurationProperties(KiswichProperties::class, PlatProperties::class)
@ComponentScan("cn.com.scooper.autoconfigure.componet")
open class KiswichRetrofitAutoConfiguration(
        val simpleExecutorService: SimpleExecutorService,
        val kiswichProperties: KiswichProperties,
        val tokenInterceptor: TokenInterceptor,
        val platProperties: PlatProperties
) {

    @Bean("retrofitGson")
    @ConditionalOnProperty(name = ["scooper.default.retrofit.enable"], havingValue = "true", matchIfMissing = true)
    @ConditionalOnMissingBean
    open fun gson(): Gson {
        return GsonBuilder()
                .setDateFormat(kiswichProperties.retrofit.dateFormat)
                .create()
    }

    @Bean("okhttpDispatch")
    @ConditionalOnProperty(name = ["scooper.default.retrofit.enable"], havingValue = "true", matchIfMissing = true)
    @ConditionalOnMissingBean
    open fun dispatcher(): Dispatcher {
        return Dispatcher(simpleExecutorService)
    }

    @Bean("scooper-core-retrofit")
    @ConditionalOnProperty(name = ["scooper.default.retrofit.enable"], havingValue = "true", matchIfMissing = true)
    @ConditionalOnMissingBean
    open fun retrofitScooperCore(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(platProperties.scooperCoreUrl)
                .client(OkHttpClient.Builder()
                        .addInterceptor(tokenInterceptor)
                        .dispatcher(dispatcher())
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .build()
    }

    @Bean("scooper-core-retrofit")
    @ConditionalOnProperty(name = ["scooper.default.retrofit.enable"], havingValue = "true", matchIfMissing = true)
    @ConditionalOnMissingBean
    open fun scooperCoreAccountService(): ScooperCoreAccountService {
        return retrofitScooperCore().create(ScooperCoreAccountService::class.java)
    }

}
