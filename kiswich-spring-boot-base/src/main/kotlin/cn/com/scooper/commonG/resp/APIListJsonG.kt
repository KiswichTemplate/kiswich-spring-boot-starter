package cn.com.scooper.commonG.resp

import cn.com.scooper.common.resp.ResultCode

class APIListJsonG<E>(code: Int, list: List<E>) : APIObjectJsonG<APIListG<E>>(code, APIListG<E>(list)) {

    constructor(list: List<E>) : this(ResultCode.SUCC, list) {}
}
