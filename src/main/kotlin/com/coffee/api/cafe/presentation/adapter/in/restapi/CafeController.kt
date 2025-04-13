package com.coffee.api.cafe.presentation.adapter.`in`.restapi

import com.coffee.api.cafe.application.port.inbound.FindCafe
import com.coffee.api.cafe.application.port.inbound.FindCafeArea
import com.coffee.api.cafe.application.port.inbound.FindCafeDetails
import com.coffee.api.cafe.application.port.inbound.FindRecommendCafe
import com.coffee.api.cafe.presentation.adapter.`in`.restapi.dto.response.*
import com.coffee.api.cafe.presentation.adapter.`in`.restapi.mapper.FindAllCafesResponseMapper
import com.coffee.api.cafe.presentation.adapter.`in`.restapi.mapper.GetCafeDetailsResponseMapper
import com.coffee.api.cafe.presentation.docs.CafeApi
import com.coffee.api.common.support.response.ApiResponse
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
    val findCafeDetails: FindCafeDetails,
    val findCafeArea: FindCafeArea,
    val findRecommendCafe: FindRecommendCafe,
) : CafeApi {

    @GetMapping
    override fun findAllCafes(
        @RequestParam(value = "lastCafeId", required = false) lastCafeId: UUID?,
        @RequestParam(value = "area", required = false) area: String?,
    ): ApiResponse<FindAllCafesResponseWrapper> {
        val result = findCafe.invoke(FindCafe.Query(lastCafeId, area))
        val response = FindAllCafesResponseMapper.toResponse(result)
        return ApiResponse.success(response)
    }

    @GetMapping("/details/{cafeId}")
    override fun getCafeDetails(
        @PathVariable cafeId: UUID,
    ): ApiResponse<GetCafeDetailsResponse> {
        val result = findCafeDetails.invoke(FindCafeDetails.Query(cafeId))
        val response = GetCafeDetailsResponseMapper.toResponse(result)
        return ApiResponse.success(response)
    }

    @GetMapping("/areas")
    override fun getAreas(): ApiResponse<FindCafeArea.Result> {
        val response = findCafeArea.execute(Unit)
        return ApiResponse.success(response)
    }

    @GetMapping("/recommend")
    override fun getRecommendCafes(lastGroupId: UUID?, limit: Int): ApiResponse<FindRecommendCafe.Result> {
        return ApiResponse.success(findRecommendCafe.execute(FindRecommendCafe.Query(lastGroupId, limit)))
    }
}
