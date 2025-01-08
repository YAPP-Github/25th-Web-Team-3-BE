package com.coffee.api.cafe.presentation

import com.coffee.api.cafe.application.usecase.FindCafe
import com.coffee.api.common.presentation.response.ApiResponse
import com.coffee.api.common.presentation.response.ApiResponseGenerator
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@Tag(name = "Cafe", description = "Cafe API")
@RestController
@RequestMapping("/api/v1/cafes")
class CafeController(
    val findCafe: FindCafe,
) {

    @Operation(summary = "모든 카페 조회", description = "모든 카페를 조회합니다.")
    @GetMapping
    fun findAllCafes(
        @RequestParam(value = "lastCafeId", required = false) lastCafeId: UUID?
    ): ApiResponse<ApiResponse.SuccessBody<FindCafe.Result?>> {
        val result = findCafe.execute(FindCafe.Query(lastCafeId))
        return ApiResponseGenerator.success(data = result, HttpStatus.OK)
    }
}