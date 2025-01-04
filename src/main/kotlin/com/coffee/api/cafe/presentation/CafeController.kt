package com.coffee.api.cafe.presentation

import com.coffee.api.cafe.application.usecase.FindCafe
import com.coffee.api.common.response.ApiResponse
import com.coffee.api.common.response.ApiResponseGenerator
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/cafes")
class CafeController(
    val findCafe: FindCafe,
) {

    /**
     * 홈화면 조회 api
     */
    @GetMapping
    fun findAllCafes(
    ): ApiResponse<ApiResponse.SuccessBody<FindCafe.Result?>> {
        val result = findCafe.execute(FindCafe.Query(""))
        return ApiResponseGenerator.success(data = result, HttpStatus.OK)
    }
}