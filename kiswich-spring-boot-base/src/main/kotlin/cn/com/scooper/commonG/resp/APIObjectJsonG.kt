package cn.com.scooper.commonG.resp

import cn.com.scooper.common.resp.ResultCode

open class APIObjectJsonG<T> : APIRespJsonG<T> {

    constructor(obj: T) : this(ResultCode.SUCC, obj) {}

    constructor(code: Int, obj: T) : super(code) {
        ////no base type!!!
        //        if (isBaseType(obj)) {
        //            setData(new APIObjectG(obj));
        //        } else {
        super.setData(obj)
        //        }
    }

    constructor(code: Int, message: String) : super(code, message) {}

    companion object {

        private val serialVersionUID = -6748552684040052684L

        fun isBaseType(obj: Any): Boolean {
            return (obj is Int || obj is String
                    || obj is Char || obj is Boolean
                    || obj is Byte || obj is Long
                    || obj is Float || obj is Double)
        }
    }
}
