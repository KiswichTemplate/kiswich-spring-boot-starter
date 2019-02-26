package cn.com.scooper.commonG.exception.data

import cn.com.scooper.common.resp.APIRespJson
import cn.com.scooper.common.resp.ResultCode
import cn.com.scooper.commonG.exception.NoResponseException

open class DataExistException(val id: Any?) : NoResponseException() {
    open override val result: APIRespJson
        get() = APIRespJson(ResultCode.DATA_EXIST, "id: $id entity exists")
}

