package cn.com.scooper.commonG.exception.auth

import cn.com.scooper.common.resp.APIRespJson
import cn.com.scooper.common.resp.ResultCode
import cn.com.scooper.commonG.exception.ScooperExceptionResult

open class AuthException : Exception(), ScooperExceptionResult {
   open override val result: APIRespJson
        get() = APIRespJson(ResultCode.FAIL, "登录失败")
}
