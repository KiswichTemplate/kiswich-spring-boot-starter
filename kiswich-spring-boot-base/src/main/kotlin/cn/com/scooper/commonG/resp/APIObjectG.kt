package cn.com.scooper.commonG.resp


class APIObjectG<T>(private val obj: T) {

    fun getObj(): T {
        return obj
    }
}
