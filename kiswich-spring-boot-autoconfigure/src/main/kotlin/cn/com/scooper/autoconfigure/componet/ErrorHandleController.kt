package cn.com.scooper.autoconfigure.componet

import cn.com.scooper.common.resp.APIRespJson
import cn.com.scooper.common.resp.ResultCode
import cn.com.scooper.commonG.exception.auth.AuthException
import cn.com.scooper.commonG.exception.data.NoDataException
import org.springframework.beans.propertyeditors.CustomDateEditor
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import springfox.documentation.annotations.ApiIgnore
import java.net.SocketTimeoutException
import java.text.SimpleDateFormat
import java.util.*

@RestControllerAdvice
@ApiIgnore
open class ErrorHandleController{

    @ExceptionHandler(AuthException::class)
    @ResponseBody
    fun handlerAuthException(authException: AuthException): APIRespJson {
        return authException.result
    }

    @ExceptionHandler(NoDataException::class)
    @ResponseBody
    fun handlerDataException(noDataException: NoDataException): APIRespJson {
        return noDataException.result
    }

    @ExceptionHandler(SocketTimeoutException::class)
    @ResponseBody
    fun handlerReadTimeoutException(socketTimeoutException: SocketTimeoutException): APIRespJson {
        return APIRespJson(ResultCode.CONNECTION_ERR)
    }


    @InitBinder
    protected fun initBinder(binder: WebDataBinder) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        dateFormat.isLenient = false
        binder.registerCustomEditor(Date::class.java, CustomDateEditor(dateFormat, true))
    }

}
