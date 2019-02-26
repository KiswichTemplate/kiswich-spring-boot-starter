package cn.com.scooper.commonG.shim

import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
@ApiImplicitParams(ApiImplicitParam(name = "page", dataType = "int", paramType = "query", value = "Results page you want to retrieve (0..N)"), ApiImplicitParam(name = "size", dataType = "int", paramType = "query", value = "Number of records per page."), ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
        + "Default sort order is ascending. " + "Multiple sort criteria are supported."))
annotation class ApiPageable
