package com.coffee.api.cafe.presentation.docs

import com.coffee.api.cafe.application.port.inbound.FindCafeArea
import com.coffee.api.cafe.presentation.adapter.dto.response.FindAllCafesResponseWrapper
import com.coffee.api.cafe.presentation.adapter.dto.response.GetCafeDetailsResponse
import com.coffee.api.common.support.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import java.util.UUID

@Tag(name = "Cafe", description = "Cafe API")
interface CafeApi {

    @Operation(summary = "모든 카페 조회", description = "모든 카페를 조회합니다.")
    fun findAllCafes(
        @RequestParam(value = "lastCafeId", required = false) lastCafeId: UUID?,
        @Parameter(
            description = "필터링할 지역 코드(없으면 전체 조회)",
            example = "MYEONGDONG_HOEHYEON",
        )
        @RequestParam(value = "area", required = false) area: String?,
    ): ApiResponse<FindAllCafesResponseWrapper>

    @Operation(summary = "카페 상세 조회", description = "cafeId로 해당 카페 정보를 상세 조회합니다.")
    fun getCafeDetails(
        @PathVariable(value = "cafeId") cafeId: UUID,
    ): ApiResponse<GetCafeDetailsResponse>

    @Operation(
        summary = "카페 지역 목록 조회",
        description = "카페를 필터링할 수 있는 지역 목록을 조회합니다.",
    )
    fun getAreas(): ApiResponse<FindCafeArea.Result>
}
