package cn.com.scooper.commonG.resp

import cn.com.scooper.common.resp.ResultCode
import cn.com.scooper.common.util.TimeUtils

import java.io.Serializable

open class APIRespJsonG<T> @JvmOverloads constructor(
        /**
         * 返回值，请见类：ResultCode
         */
        val code: Int = ResultCode.SUCC) : Serializable {
    /**
     * 消息key（便于 i18n 解析）
     */
    private var messageKey: String? = null
    /**
     * 消息参数（便于 i18n 解析）
     */
    private var messageParams: Array<Any>? = null
    /**
     * 信息提示，code=0时可以不填写该字段。
     */
    private var message = ""
    /**
     * 服务器系统时间。格式：2016-09-02 12:02:02
     */
    val systemTime: String
    /**
     * JSON数据部分。
     */
    private var data: T? = null

    init {
        this.systemTime = TimeUtils.currTime()
    }

    constructor(code: Int, message: String) : this(code) {
        this.message = message
    }

    fun getMessage(): String {
        return message
    }

    fun setMessage(message: String): APIRespJsonG<*> {
        this.message = message
        return this
    }

    fun getData(): T? {
        return if (data == null) null else data
    }

    fun setData(data: T): APIRespJsonG<*> {
        this.data = data
        return this
    }

    fun getMessageKey(): String? {
        return messageKey
    }

    fun setMessageKey(messageKey: String): APIRespJsonG<*> {
        this.messageKey = messageKey
        return this
    }

    fun getMessageParams(): Array<Any>? {
        return messageParams
    }

    fun setMessageParams(messageParams: Array<Any>): APIRespJsonG<*> {
        this.messageParams = messageParams
        return this
    }

    companion object {
        private const val serialVersionUID = 6354540642744803586L
    }

}
