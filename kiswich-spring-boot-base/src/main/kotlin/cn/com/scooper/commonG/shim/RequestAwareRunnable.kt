package cn.com.scooper.commonG.shim

import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder

/**
 * 包装runnable 使其具有web生命周期
 */
abstract class RequestAwareRunnable : Runnable {

    private val requestAttributes: RequestAttributes?
    private var thread: Thread? = null

    init {
        this.requestAttributes = RequestContextHolder.getRequestAttributes()
        this.thread = Thread.currentThread()
    }

    override fun run() {
        try {
            RequestContextHolder.setRequestAttributes(requestAttributes)
            onRun()
        } finally {
            if (Thread.currentThread() !== thread) {
                RequestContextHolder.resetRequestAttributes()
            }
            thread = null
        }
    }

    protected abstract fun onRun()
}
