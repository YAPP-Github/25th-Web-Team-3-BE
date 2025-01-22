package com.coffee.api.config

import com.coffee.api.common.support.error.CoreApiException
import com.coffee.api.common.support.error.ErrorType
import com.coffee.api.common.support.response.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@io.swagger.v3.oas.annotations.Hidden
class ApiControllerAdvice {
    @ExceptionHandler(value = [CoreApiException::class])
    fun handleCoreApiException(e: CoreApiException): ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity(ApiResponse.error(e.errorType, e.data), e.errorType.status)
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Any>> {
        return ResponseEntity(ApiResponse.error(ErrorType.DEFAULT_ERROR, e.message), ErrorType.DEFAULT_ERROR.status)
    }
}
