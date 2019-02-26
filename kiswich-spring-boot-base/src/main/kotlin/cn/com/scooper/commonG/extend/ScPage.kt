package cn.com.scooper.commonG.extend

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.ModelAttribute

/**
 * 以 [ModelAttribute] 的方式引用，配合 [Pageable]使用，目的是适配pageNum和pageSize字段
 */

class ScPage(
        var pageNum: Int? = null,
        var pageSize: Int? = null
) {
    /**
     * 优先级大于pageable的字段
     */
    fun setToPageable(pageable: Pageable): Pageable {
        return pageable.let {
            return@let PageRequest.of(
                    if (pageNum != null) pageNum!! - 1 else it.pageNumber,
                    pageSize ?: it.pageSize,
                    it.sort
            )
        }
    }
}
