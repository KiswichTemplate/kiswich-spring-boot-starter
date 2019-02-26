package cn.com.scooper.commonG.extend

import cn.com.scooper.common.util.TimeUtils
import java.io.OutputStream
import java.net.URLEncoder
import java.util.*
import javax.servlet.http.HttpServletResponse

fun javax.servlet.http.HttpServletResponse.returnFile(filename: String): HttpServletResponse {
    this.contentType = "application/octet-stream"
    this.setHeader("Content-Disposition", "attachment;fileName=${URLEncoder.encode(filename, "UTF-8")}")
    return this
}

fun String.wrapperToXlsx(): String {
    val toString = TimeUtils.toString(Date(), "yyyyMMddHHmmss")
    return "$this-$toString.xlsx"
}

/**
 * 用于返回文件的语法糖
 * Example usage:
 * ``` kotlin
 *      @GetMapping("/foo")
 *      fun exportSth(httpServletResponse: HttpServletResponse){
 *          httpServletResponse.returnFile("export.excel"){ outputStream ->
 *              foo(outputStream)
 *          }
 *      }
 *
 * ```
 */
fun javax.servlet.http.HttpServletResponse.returnFile(filename: String, callback: (OutputStream) -> Unit) {
    this.returnFile(filename)
    this.outputStream.use {
        callback(it)
        it.flush()
    }
}

fun javax.servlet.http.HttpServletResponse.returnImage(callback: (OutputStream) -> Unit) {
    this.contentType = "image/png"
    this.outputStream.use {
        callback(it)
        it.flush()
    }
}
