package cn.com.scooper.commonG.resp

open class APIListG<E> protected constructor(val list: List<E>?, total: Long) {

    var total: Long = 0
        protected set

    constructor(list: List<E>?) : this(list, (list?.count() ?: 0).toLong()) {}

    init {
        this.total = total
    }

    companion object {

        private val serialVersionUID = 4876647144775880803L
    }
}
