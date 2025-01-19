package com.coffee.api.common.support.error

import org.springframework.http.HttpStatus

enum class ErrorType(val status: HttpStatus, val code: ErrorCode, val message: String) {
    DEFAULT_ERROR(
        HttpStatus.INTERNAL_SERVER_ERROR,
        ErrorCode.INTERNAL_SERVER_ERROR_500,
        "An unexpected error occurred."
    ),

    BAD_REQUEST(
        HttpStatus.BAD_REQUEST,
        ErrorCode.CLIENT_REQUEST_ERROR_400,
        "The request is invalid."
    ),

    UNAUTHORIZED(
        HttpStatus.UNAUTHORIZED,
        ErrorCode.UNAUTHORIZED_401,
        "Authentication is required to access this resource."
    ),

    FORBIDDEN(
        HttpStatus.FORBIDDEN,
        ErrorCode.FORBIDDEN_403,
        "You do not have permission to access this resource."
    ),

    NOT_FOUND(
        HttpStatus.NOT_FOUND,
        ErrorCode.NOT_FOUND_404,
        "The requested resource could not be found."
    ),

    CONFLICT(
        HttpStatus.CONFLICT,
        ErrorCode.CONFLICT_409,
        "A conflict occurred while processing the request."
    ),

    VALIDATION_ERROR(
        HttpStatus.UNPROCESSABLE_ENTITY,
        ErrorCode.VALIDATION_ERROR_422,
        "The request contains invalid data or parameters."
    )
}
