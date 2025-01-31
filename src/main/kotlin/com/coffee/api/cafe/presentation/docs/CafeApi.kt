package com.coffee.api.cafe.presentation.docs

import com.coffee.api.cafe.application.port.inbound.FindRecommendCafe
import com.coffee.api.cafe.presentation.adapter.dto.response.FindAllCafesResponseWrapper
import com.coffee.api.cafe.presentation.adapter.dto.response.GetCafeDetailsResponse
import com.coffee.api.common.presentation.constant.SliceConstants
import com.coffee.api.common.support.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

@Tag(name = "Cafe", description = "Cafe API")
interface CafeApi {

    @Operation(summary = "모든 카페 조회", description = "모든 카페를 조회합니다.")
    fun findAllCafes(
        @RequestParam(value = "lastCafeId", required = false) lastCafeId: UUID?,
    ): ApiResponse<FindAllCafesResponseWrapper>

    @Operation(summary = "카페 상세 조회", description = "cafeId로 해당 카페 정보를 상세 조회합니다.")
    fun getCafeDetails(
        @PathVariable(value = "cafeId") cafeId: UUID,
    ): ApiResponse<GetCafeDetailsResponse>

    @Operation(summary = "카페 추천 조회", description = "그룹화된 카페 추천을 조회합니다.")
    fun getRecommendCafes(
        @Parameter(
            description = "마지막으로 조회된 카페 ID",
        )
        @RequestParam(value = "lastCafeId", required = false)
        lastCafeId: UUID?,

        @Parameter(
            description = "한 번에 조회할 데이터 수",
            example = "5",
            schema = Schema(
                minimum = SliceConstants.MIN_LIMIT.toString(),
                defaultValue = SliceConstants.DEFAULT_LIMIT.toString(),
            ),
        )
        @RequestParam(value = "limit", required = false, defaultValue = SliceConstants.DEFAULT_LIMIT.toString())
        limit: Int = 5,
    ): ApiResponse<FindRecommendCafe.Result>
}
