package com.coffee.api.cafe.presentation.adapter.controller

import com.coffee.api.cafe.application.port.inbound.FindCafe
import com.coffee.api.cafe.application.port.inbound.FindCafeDetails
import com.coffee.api.cafe.presentation.adapter.dto.response.*
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
) : CafeApi {

    @GetMapping
    override fun findAllCafes(
        @RequestParam(value = "lastCafeId", required = false) lastCafeId: UUID?,
    ): ApiResponse<FindAllCafesResponseWrapper> {
        val result = findCafe.invoke(FindCafe.Query(lastCafeId))

        val cafes = result.cafes.map { cafe ->
            FindAllCafesResponse(
                cafeId = cafe.cafeId.value.toString(),
                name = cafe.name,
                engName = "",
                nearestStation = cafe.nearestStation,
                location = cafe.location,
                price = cafe.price,
                previewImages = cafe.previewImages ?: "",
                tags = cafe.tags.map { tag ->
                    TagResponse(
                        id = tag.id.value.toString(),
                        name = tag.name
                    )
                }
            )
        }

        val response = FindAllCafesResponseWrapper(
            cafes = cafes,
            hasNext = result.hasNext
        )

        return ApiResponse.success(response)
    }

    @GetMapping("/details/{cafeId}")
    override fun getCafeDetails(
        @PathVariable cafeId: UUID,
        ): ApiResponse<GetCafeDetailsResponse> {
        val result = findCafeDetails.invoke(FindCafeDetails.Query(cafeId))

        val response = GetCafeDetailsResponse(
            cafe = CafeResponse(
                id = result.cafeDetails.cafe.id.value.toString(),
                naverMapUrl = result.cafeDetails.cafe.naverMapUrl,
                name = result.cafeDetails.cafe.name,
                nearestStation = result.cafeDetails.cafe.nearestStation,
                location = result.cafeDetails.cafe.location,
                price = result.cafeDetails.cafe.price,
                mainImageUrl = result.cafeDetails.cafe.mainImages,
            ),
            coffeeBean = result.cafeDetails.coffeeBean,
            menus = result.cafeDetails.menu.map { menu ->
                MenuResponse(
                    id = menu.id.value.toString(),
                    price = menu.price,
                    imageUrl = menu.imageUrl,
                    description = menu.description,
                )
            },
            tags = result.cafeDetails.tag.map { tag ->
                TagResponse(
                    id = tag.id.value.toString(),
                    name = tag.name
                )
            },
            updatedAt = result.cafeDetails.updatedAt,
        )

        return ApiResponse.success(response)
    }
}
