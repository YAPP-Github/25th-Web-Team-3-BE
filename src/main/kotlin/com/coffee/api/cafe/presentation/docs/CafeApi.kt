package com.coffee.api.cafe.presentation.docs

import com.coffee.api.cafe.application.usecase.FindCafe
import com.coffee.api.common.presentation.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Tag(name = "Cafe", description = "Cafe API")
interface CafeApi {

    @Operation(summary = "모든 카페 조회", description = "모든 카페를 조회합니다.")
    fun findAllCafes(
        @RequestParam(value = "lastCafeId", required = false) lastCafeId: UUID?,
    ): ApiResponse<ApiResponse.SuccessBody<FindCafe.Result?>>
}
