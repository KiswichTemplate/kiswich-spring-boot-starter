package cn.com.scooper.autoconfigure.componet

import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.SessionScope

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
open class SessionToken(
        var token: String? = null
)
