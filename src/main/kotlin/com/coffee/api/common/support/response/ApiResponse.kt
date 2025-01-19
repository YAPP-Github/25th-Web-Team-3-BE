package com.coffee.api.common.support.response

import com.coffee.api.common.support.error.ErrorMessage
import com.coffee.api.common.support.error.ErrorType

class ApiResponse<T> private constructor(
    val result: ResultType,
    val data: T? = null,
    val error: ErrorMessage? = null,
){
    companion object{
        fun success(): ApiResponse<Any> = ApiResponse(ResultType.SUCCESS, null, null)
        fun <T> success(data: T): ApiResponse<T> = ApiResponse(ResultType.SUCCESS, data, null)
        fun <T> error(error: ErrorType, errorData: Any? = null): ApiResponse<T> = ApiResponse(ResultType.ERROR, null, ErrorMessage(error, errorData))
    }
}
