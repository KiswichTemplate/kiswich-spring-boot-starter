package cn.com.scooper.commonG.shim

import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder

import java.util.concurrent.Callable

/**
 * @see RequestAwareRunnable
 *
 * @param <V>
</V> */
abstract class ContextAwareCallable<V> : Callable<V> {
    private val requestAttributes: RequestAttributes?
    private var thread: Thread? = null

    init {
        this.requestAttributes = RequestContextHolder.getRequestAttributes()
        this.thread = Thread.currentThread()
    }

    @Throws(Exception::class)
    override fun call(): V {
        try {
            RequestContextHolder.setRequestAttributes(requestAttributes)
            return onCall()
        } finally {
            if (Thread.currentThread() !== thread) {
                RequestContextHolder.resetRequestAttributes()
            }
            thread = null
        }
    }

    @Throws(Exception::class)
    abstract fun onCall(): V
}
