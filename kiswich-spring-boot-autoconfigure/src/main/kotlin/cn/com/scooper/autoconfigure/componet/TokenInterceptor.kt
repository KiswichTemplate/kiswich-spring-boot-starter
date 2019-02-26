package cn.com.scooper.autoconfigure.componet

import okhttp3.Interceptor
import okhttp3.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class TokenInterceptor @Autowired
constructor(private val sessionToken: SessionToken) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (sessionToken.token != null) {
            val url = chain.request().url().newBuilder().addEncodedQueryParameter("token", sessionToken.token).build()
            request = chain.request().newBuilder().url(url).build()

        }
        return chain.proceed(request)
    }
}
