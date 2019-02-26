package cn.com.scooper.commonG.resp

open class APIPageJsonG<E> : APIObjectJsonG<APIPageG<E>> {

    constructor(page: APIPageG<E>) : super(page) {}

    constructor(list: List<E>, total: Long, pageNumber: Int, pageSize: Int) : this(APIPageG<E>(list, total, pageNumber, pageSize)) {}

    @JvmOverloads
    constructor(code: Int, message: String = "") : super(code, message) {
    }

    companion object {

        private val serialVersionUID = 2798126123091247602L
    }

}
