package cn.com.scooper.commonG.resp

import cn.com.scooper.common.resp.APIList

class APIPageG<E>(list: List<E>, total: Long, val pageNumber: Int // 页码，从1开始
                  , val pageSize: Int // 每页记录数
) : APIListG<E>(list, total) {
    companion object {
        private val serialVersionUID = 1949526072549158586L
    }

}

