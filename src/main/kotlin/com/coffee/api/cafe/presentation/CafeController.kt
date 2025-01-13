package com.coffee.api.cafe.presentation

import com.coffee.api.cafe.application.usecase.FindCafe
import com.coffee.api.cafe.application.usecase.FindCafeDetails
import com.coffee.api.cafe.presentation.docs.CafeApi
import com.coffee.api.common.presentation.response.ApiResponse
import com.coffee.api.common.presentation.response.ApiResponseGenerator
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/cafes")
class CafeController(
    val findCafe: FindCafe,
) : CafeApi {

    @GetMapping
    override fun findAllCafes(
        @RequestParam(value = "lastCafeId", required = false) lastCafeId: UUID?,
    ): ApiResponse<ApiResponse.SuccessBody<FindCafe.Result?>> {
        val result = findCafe.execute(FindCafe.Query(lastCafeId))
        return ApiResponseGenerator.success(data = result, HttpStatus.OK)
    }

    @GetMapping("/cafeId")
    override fun getCafeDetails(
        @PathVariable cafeId: UUID,
        ): ApiResponse<ApiResponse.SuccessBody<FindCafeDetails.Result?>> {
        TODO("Not yet implemented")
    }
}
