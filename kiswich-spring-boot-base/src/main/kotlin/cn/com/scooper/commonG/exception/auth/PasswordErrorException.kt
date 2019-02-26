package cn.com.scooper.commonG.exception.auth

import cn.com.scooper.common.resp.APIRespJson
import cn.com.scooper.common.resp.ResultCode
import cn.com.scooper.commonG.exception.auth.AuthException

open class PasswordErrorException : AuthException() {
   open override val result: APIRespJson
        get() = APIRespJson(ResultCode.AUTH_PASSWORD_ERR, "密码错误")
}
