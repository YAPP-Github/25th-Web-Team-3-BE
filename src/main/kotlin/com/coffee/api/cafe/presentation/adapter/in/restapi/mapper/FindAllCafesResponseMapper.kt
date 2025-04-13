package com.coffee.api.cafe.presentation.adapter.`in`.restapi.mapper

import com.coffee.api.cafe.application.port.inbound.FindCafe
import com.coffee.api.cafe.presentation.adapter.`in`.restapi.dto.response.FindAllCafesResponse
import com.coffee.api.cafe.presentation.adapter.`in`.restapi.dto.response.FindAllCafesResponseWrapper
import com.coffee.api.cafe.presentation.adapter.`in`.restapi.dto.response.TagResponse

object FindAllCafesResponseMapper {
    fun toResponse(result: FindCafe.Result): FindAllCafesResponseWrapper {
        val cafes = result.cafes.map { cafe ->
            FindAllCafesResponse(
                cafeId = cafe.cafeId.value.toString(),
                name = cafe.name,
                nearestStation = cafe.nearestStation,
                location = cafe.location,
                price = cafe.price,
                previewImages = cafe.previewImages,
                tags = cafe.tags.map { tag ->
                    TagResponse(
                        id = tag.id.value.toString(),
                        name = tag.name,
                    )
                }
            )
        }
        return FindAllCafesResponseWrapper(
            cafes = cafes,
            hasNext = result.hasNext,
        )
    }
}
