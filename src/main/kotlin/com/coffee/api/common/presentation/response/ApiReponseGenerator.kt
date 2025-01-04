package com.coffee.api.common.response

import org.springframework.http.HttpStatus

object ApiResponseGenerator {
    fun <D> success(
        data: D?,
        status: HttpStatus,
    ): ApiResponse<ApiResponse.SuccessBody<D?>> =
        ApiResponse(
            ApiResponse.SuccessBody(
                data,
            ),
            status,
        )

    fun fail(
        message: String = "",
        status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
        code: Int = 0,
    ): ApiResponse<ApiResponse.FailureBody> =
        ApiResponse(ApiResponse.FailureBody(message = "[ERROR] : $message", code = code), status)
}
