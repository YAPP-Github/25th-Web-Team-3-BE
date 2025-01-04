package com.coffee.api.common.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.io.Serializable

class ApiResponse<B> : ResponseEntity<B> {
    constructor(status: HttpStatus) : super(status)
    constructor(body: B, status: HttpStatus) : super(body, status)

    class FailureBody(
        val message: String,
        val code: Int,
    ) : Serializable

    class SuccessBody<D>(
        val data: D,
    ) : Serializable
}
