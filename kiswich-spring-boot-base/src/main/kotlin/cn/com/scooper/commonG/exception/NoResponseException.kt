package cn.com.scooper.commonG.exception

import cn.com.scooper.common.resp.APIRespJson
import cn.com.scooper.common.resp.ResultCode

open class NoResponseException : Exception(), ScooperExceptionResult {
    open override val result: APIRespJson
        get() = APIRespJson(ResultCode.NO_RESPONDING)
}
