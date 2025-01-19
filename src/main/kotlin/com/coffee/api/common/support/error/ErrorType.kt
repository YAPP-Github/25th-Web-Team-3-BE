package com.coffee.api.common.support.error

import org.springframework.http.HttpStatus

enum class ErrorType(val status: HttpStatus, val code: ErrorCode, val message: String) {
    DEFAULT_ERROR(
        HttpStatus.INTERNAL_SERVER_ERROR,
        ErrorCode.INTERNAL_SERVER_ERROR,
        "An unexpected error occurred."
    ),

    BAD_REQUEST(
        HttpStatus.BAD_REQUEST,
        ErrorCode.BAD_REQUEST,
        "The request is invalid."
    ),

    UNAUTHORIZED(
        HttpStatus.UNAUTHORIZED,
        ErrorCode.UNAUTHORIZED,
        "Authentication is required to access this resource."
    ),

    FORBIDDEN(
        HttpStatus.FORBIDDEN,
        ErrorCode.FORBIDDEN,
        "You do not have permission to access this resource."
    ),

    NOT_FOUND(
        HttpStatus.NOT_FOUND,
        ErrorCode.NOT_FOUND,
        "The requested resource could not be found."
    ),

    CONFLICT(
        HttpStatus.CONFLICT,
        ErrorCode.CONFLICT,
        "A conflict occurred while processing the request."
    ),

    VALIDATION_ERROR(
        HttpStatus.UNPROCESSABLE_ENTITY,
        ErrorCode.VALIDATION_ERROR,
        "The request contains invalid data or parameters."
    )
}
