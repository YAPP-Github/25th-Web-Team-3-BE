package com.coffee.api.foo.presentation

import com.coffee.api.common.presentation.response.ApiResponse
import com.coffee.api.common.presentation.response.ApiResponseGenerator
import com.coffee.api.foo.application.usecase.CreateFoo
import com.coffee.api.foo.presentation.request.SaveFooRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FooController(
    val createFoo: CreateFoo
) {
    @PostMapping("/foo")
    fun save(
        @RequestBody request: SaveFooRequest,
    ): ApiResponse<ApiResponse.SuccessBody<CreateFoo.Result?>> {
        val result = createFoo.execute(CreateFoo.Command(request.value))
        return ApiResponseGenerator.success(data = result, HttpStatus.CREATED)
    }
}
