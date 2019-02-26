package cn.com.scooper.commonG.exception.data

import cn.com.scooper.common.resp.APIRespJson
import cn.com.scooper.common.resp.ResultCode
import cn.com.scooper.commonG.exception.ScooperExceptionResult

open class NoDataException(open val objectId: Any?) : Exception(), ScooperExceptionResult {

   open override val result: APIRespJson
        get() = APIRespJson(ResultCode.DATA_NOEXIST, "id: $objectId entity not exist")

}
