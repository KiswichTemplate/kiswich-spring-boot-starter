package cn.com.scooper.commonG.util

import org.springframework.beans.BeanUtils
import org.springframework.beans.BeanWrapperImpl
import java.util.*

/**
 * 泛型封装beanUtils
 */
object SimpleBeanUtils {

    @JvmStatic
    fun <T : Any> copyBean(source: Any, destClass: Class<out T>, vararg ignoreProperties: String): T {
        var instance: T? = null
        instance = destClass.getDeclaredConstructor().newInstance()
        BeanUtils.copyProperties(source, instance!!, *ignoreProperties)
        return instance
    }

    @JvmStatic
    fun <T : Any> copyBeanBatch(sources: Array<Any>, destClass: Class<out T>, vararg ignoreProperties: String): List<T> {
        val arrayList = ArrayList<T>()
        for (source in sources) {
            var t = destClass.getDeclaredConstructor().newInstance()
            BeanUtils.copyProperties(source, t!!, *ignoreProperties)
            arrayList.add(t)
        }
        return arrayList
    }

    @JvmStatic
    fun <T : Any> copyBeanBatch(sources: Iterable<*>, destClass: Class<out T>, vararg ignoreProperties: String): List<T> {
        val arrayList = ArrayList<T>()
        for (source in sources) {
            var t: T = destClass.getDeclaredConstructor().newInstance()
            BeanUtils.copyProperties(source!!, t, *ignoreProperties)//强迫症
            arrayList.add(t)
        }
        return arrayList
    }

    @JvmStatic
    fun <E : Any, T : Any> copyBeanBatchCallBack(sources: Iterable<out E>, destClass: Class<out T>, callback: (E, T) -> Unit): List<T> {
        val arrayList = ArrayList<T>()
        for (source in sources) {
            var t: T = destClass.getDeclaredConstructor().newInstance()
            BeanUtils.copyProperties(source, t)
            callback(source, t)
            arrayList.add(t)
        }
        return arrayList
    }

    @JvmStatic
    fun copyNonNullProperties(src: Any, target: Any?, vararg ignoreProperties: String) {
        BeanUtils.copyProperties(src, target!!, *ignoreProperties, *getNullPropertyNames(src))
    }

    @JvmStatic
    fun <T : Any> copyNonNullPropertiesClone(src: Any?, target: T, targetClass: Class<out T>): T? {
        if (src == null) return null;
        val copyBean = copyBean(target, targetClass)
        copyNonNullProperties(src, copyBean)
        return copyBean
    }

    @JvmStatic
    fun getNullPropertyNames(source: Any): Array<String> {
        val src = BeanWrapperImpl(source)
        val pds = src.propertyDescriptors

        val emptyNames = HashSet<String>()
        for (pd in pds) {
            val srcValue = src.getPropertyValue(pd.name)
            if (srcValue == null) emptyNames.add(pd.name)
        }
        val result = arrayOfNulls<String>(emptyNames.size)
        return emptyNames.toTypedArray()
    }


}
