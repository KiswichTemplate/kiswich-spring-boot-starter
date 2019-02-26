package cn.com.scooper.commonG.extend

import cn.com.scooper.commonG.util.SimpleBeanUtils

/**
 * bean 拷贝的函数式调用
 */

fun <S : Any, D : Any> S.copyBean(clazz: Class<D>, vararg ignoreProperties: String): D {
    return SimpleBeanUtils.copyBean(this, clazz, *ignoreProperties)
}

/**
 * 批量拷贝
 */
fun <S : Iterable<Any>, D : Any> S.copyBeanBatch(clazz: Class<D>, vararg ignoreProperties: String): List<D> {
    return SimpleBeanUtils.copyBeanBatch(this, clazz, *ignoreProperties)
}

/**
 * 批量拷贝并执行每次拷贝的回调，主要用于一些特定属性的转换
 */
fun <S : List<E>, E : Any, D : Any> S.copyBeanBatchCallback(clazz: Class<D>, callback: (E, D) -> Unit): List<D> {
    return SimpleBeanUtils.copyBeanBatchCallBack(this, clazz, callback)
}

/**
 * 拷贝自身非空字段到目标对象
 */
fun <T : Any> Any.copyNotNullFieldTo(target: T): T {
    SimpleBeanUtils.copyNonNullProperties(this, target)
    return target
}
