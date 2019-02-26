package cn.com.scooper.commonG.exception.auth

import cn.com.scooper.common.resp.APIRespJson
import cn.com.scooper.common.resp.ResultCode
import cn.com.scooper.commonG.exception.auth.AuthException

open class EmptyAccountException : AuthException() {

   open override val result: APIRespJson
        get() = APIRespJson(ResultCode.AUTH_USER_NOEXIST, "不存在账号")
}
