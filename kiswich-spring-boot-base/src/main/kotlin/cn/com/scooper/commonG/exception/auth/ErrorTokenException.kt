package cn.com.scooper.commonG.exception.auth

import cn.com.scooper.common.resp.APIRespJson
import cn.com.scooper.common.resp.ResultCode
import cn.com.scooper.commonG.exception.auth.AuthException

open class ErrorTokenException : AuthException() {
   open override val result: APIRespJson
        get() = APIRespJson(ResultCode.TOKEN_EXPIRED, "token已失效或不存在")
}
