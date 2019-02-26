package cn.com.scooper.autoconfigure.aftercomponent

import cn.com.scooper.autoconfigure.componet.SessionToken
import cn.com.scooper.autoconfigure.service.ScooperCoreAccountService
import cn.com.scooper.common.resp.ResultCode
import cn.com.scooper.commonG.exception.auth.AuthException
import cn.com.scooper.commonG.exception.auth.EmptyTokenException
import cn.com.scooper.commonG.exception.auth.ErrorTokenException

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * token优先级由低到高
 * 1. cookie
 * 2. header
 * 3. query
 */
@Component
class AuthInterceptor @Autowired
constructor(
        private val scooperCoreAccountService: ScooperCoreAccountService,
        private val sessionToken: SessionToken) : HandlerInterceptor {

    @Throws(AuthException::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        var token: String? = null

        token = request.getParameter("token")

        if (token == null)
            token = request.getHeader("token")

        if (token == null && request.cookies != null)
            for (cookie in request.cookies) {
                if (cookie.name == "name") {
                    token = cookie.value
                }
            }

        if (token == null) throw EmptyTokenException()
        if (token == sessionToken.token)
            return true
        else {
            val apiRespJsonCall = scooperCoreAccountService.verifyToken(token)
            val execute = apiRespJsonCall.execute()
            if (execute.body()!!.code == ResultCode.SUCC) {
                sessionToken.token = token
                return true
            }
        }

        throw ErrorTokenException()
    }

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {

    }

    @Throws(Exception::class)
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {

    }
}
