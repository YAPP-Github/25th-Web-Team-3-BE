package com.coffee.api.cafe.presentation.docs

import com.coffee.api.cafe.application.usecase.FindCafe
import com.coffee.api.cafe.application.usecase.FindCafeDetails
import com.coffee.api.common.support.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

@Tag(name = "Cafe", description = "Cafe API")
interface CafeApi {

    @Operation(summary = "모든 카페 조회", description = "모든 카페를 조회합니다.")
    fun findAllCafes(
        @RequestParam(value = "lastCafeId", required = false) lastCafeId: UUID?,
    ): ApiResponse<Any>

    @Operation(summary = "카페 상세 조회", description = "cafeId로 해당 카페 정보를 상세 조회합니다.")
    fun getCafeDetails(
        @PathVariable(value = "cafeId") cafeId: UUID,
    ): ApiResponse<Any>
}
