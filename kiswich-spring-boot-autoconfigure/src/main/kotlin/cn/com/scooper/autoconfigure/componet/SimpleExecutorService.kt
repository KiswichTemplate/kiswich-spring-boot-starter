package cn.com.scooper.autoconfigure.componet

import cn.com.scooper.commonG.shim.ContextAwareCallable
import cn.com.scooper.commonG.shim.RequestAwareRunnable
import org.springframework.core.task.support.ExecutorServiceAdapter
import org.springframework.stereotype.Component
import java.util.concurrent.*

/**
 * 包装线程池 代理对象
 */
@Component
class SimpleExecutorService(
        val executorService: ExecutorServiceAdapter
) : ExecutorService {

    private fun wrapperRunnable(runnable: Runnable): RequestAwareRunnable {
        return object : RequestAwareRunnable() {
            protected override fun onRun() {
                runnable.run()
            }
        }
    }

    private fun <T> wrapperCallable(callable: Callable<T>): ContextAwareCallable<T> {
        return object : ContextAwareCallable<T>() {
            @Throws(Exception::class)
            override fun onCall(): T {
                return callable.call()
            }
        }
    }


    override fun shutdown() {
        executorService.shutdown()
    }

    override fun shutdownNow(): List<Runnable> {
        return executorService.shutdownNow()
    }

    override fun isShutdown(): Boolean {
        return executorService.isShutdown
    }

    override fun isTerminated(): Boolean {
        return executorService.isTerminated
    }

    @Throws(InterruptedException::class)
    override fun awaitTermination(timeout: Long, unit: TimeUnit): Boolean {
        return executorService.awaitTermination(timeout, unit)
    }

    override fun <T> submit(task: Callable<T>): Future<T> {
        return executorService.submit<T>(wrapperCallable(task))
    }

    override fun <T> submit(task: Runnable, result: T): Future<T> {
        return executorService.submit(wrapperRunnable(task), result)
    }

    override fun submit(task: Runnable): Future<*> {
        return executorService.submit(wrapperRunnable(task))
    }

    @Throws(InterruptedException::class)
    override fun <T> invokeAll(tasks: Collection<Callable<T>>): List<Future<T>> {
        return executorService.invokeAll<T>(tasks.map { this.wrapperCallable(it) })
    }

    @Throws(InterruptedException::class)
    override fun <T> invokeAll(tasks: Collection<Callable<T>>, timeout: Long, unit: TimeUnit): List<Future<T>> {

        return executorService.invokeAll<T>(
                tasks.map { this.wrapperCallable(it) },
                timeout,
                unit
        )
    }

    @Throws(InterruptedException::class, ExecutionException::class)
    override fun <T> invokeAny(tasks: Collection<Callable<T>>): T {
        return executorService.invokeAny<T>(tasks.map { this.wrapperCallable(it) })
    }

    @Throws(InterruptedException::class, ExecutionException::class, TimeoutException::class)
    override fun <T> invokeAny(tasks: Collection<Callable<T>>, timeout: Long, unit: TimeUnit): T {
        return executorService.invokeAny<T>(tasks.map { this.wrapperCallable(it) }, timeout, unit)
    }

    override fun execute(command: Runnable) {
        val runnable = wrapperRunnable(command)
        executorService.execute(runnable)
    }
}
