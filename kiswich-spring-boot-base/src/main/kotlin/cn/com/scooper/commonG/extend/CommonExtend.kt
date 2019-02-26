package cn.com.scooper.commonG.extend

import cn.com.scooper.commonG.resp.APIListJsonG
import cn.com.scooper.commonG.resp.APIObjectJsonG
import cn.com.scooper.commonG.resp.APIPageJsonG
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.support.Querydsl
import java.lang.Exception
import java.math.BigDecimal
import java.util.*
import java.util.function.Supplier

/**
 * 转为对象返回
 */
fun <T> T.toAPIObjectJsonG(): APIObjectJsonG<T> {
    return APIObjectJsonG(this)
}

/**
 * 转为分页返回
 */
fun <T> Page<out T>.toAPIPageJson(): APIPageJsonG<T> {
    return APIPageJsonG(this.content as List<T>, this.totalElements, this.number + 1, this.size)
}

/**
 * 转为数组返回
 */
fun <T> Iterable<out T>.toAPIListJsonG():APIListJsonG<T>{
    return APIListJsonG(this.toList())
}

/**
 * 更新pageable， 主要是为了适配公司接口
 */
fun Pageable.change(pageNum: Int? = this.pageNumber, pageSize: Int? = this.pageSize, sort: Sort? = this.sort): Pageable {
    return PageRequest.of(
            if (pageNum != null) pageNum - 1 else this.pageNumber,
            pageSize ?: this.pageSize,
            sort ?: this.sort
    )
}

/**
 * 分页的执行部分+组装
 * @see applyPage
 */
@Deprecated("封装不完全，建议使用applyPage")
fun <T> com.querydsl.jpa.JPQLQuery<T>.pageSearch(pageable: Pageable): Page<T> {
    val list = this.fetch()
    val count = this.fetchCount()
    return org.springframework.data.domain.PageImpl(list, pageable, count)
}

/**
 * 用于结尾做分页查询，将分页查询和结果组合了
 */
fun <T> com.querydsl.jpa.JPQLQuery<T>.applyPage(pageable: Pageable, querydsl: Querydsl): Page<T> {
    val jpqlQuery = querydsl.applyPagination(pageable, this)

    val list = jpqlQuery.fetch()
    val count = jpqlQuery.fetchCount()
    return org.springframework.data.domain.PageImpl(list, pageable, count)
}

fun BigDecimal.isZero(): Boolean {
    return this.compareTo(BigDecimal.ZERO) == 0
}

fun BigDecimal.notZero(): Boolean {
    return this.compareTo(BigDecimal.ZERO) != 0
}

fun Double.format(format: String): String {
    return String.format(format, this)
}

fun Float.format(format: String): String {
    return String.format(format, this)
}


fun java.sql.Timestamp.toCalendar(): Calendar {
    val instance = Calendar.getInstance()
    instance.time = this
    return instance
}

/**
 * 适配java8 optional的orElseThrow，由于kotlin构造 [Supplier] 比较麻烦
 */
fun <T>Optional<T>.orElseThrow(exception: Exception): T {
    if(this.isPresent){
        return this.get()
    }else{
        throw exception
    }
}


