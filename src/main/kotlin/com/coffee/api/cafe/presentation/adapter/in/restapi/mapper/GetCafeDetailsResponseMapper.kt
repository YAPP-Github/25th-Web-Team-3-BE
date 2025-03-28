package com.coffee.api.cafe.presentation.adapter.`in`.restapi.mapper

import com.coffee.api.cafe.application.port.inbound.FindCafeDetails
import com.coffee.api.cafe.presentation.adapter.`in`.restapi.dto.response.*

object GetCafeDetailsResponseMapper {
    fun toResponse(result: FindCafeDetails.Result): GetCafeDetailsResponse {
        return GetCafeDetailsResponse(
            cafe = CafeResponse(
                id = result.cafeDetails.cafe.id.value.toString(),
                reasonForSelection = result.cafeDetails.cafe.reasonForSelection,
                naverMapUrl = result.cafeDetails.cafe.naverMapUrl,
                name = result.cafeDetails.cafe.name,
                nearestStation = result.cafeDetails.cafe.nearestStation,
                location = result.cafeDetails.cafe.location,
                price = result.cafeDetails.cafe.price,
                mainImageUrl = result.cafeDetails.cafe.mainImages,
            ),
            coffeeBean = CoffeeBeanResponse(
                id = result.cafeDetails.coffeeBean.id.value.toString(),
                description = result.cafeDetails.coffeeBean.description,
                name = result.cafeDetails.coffeeBean.name,
                engName = result.cafeDetails.coffeeBean.engName,
                flavors = result.cafeDetails.coffeeBean.flavors.map { flavor ->
                    FlavorResponse(
                        flavor.displayName,
                        flavor.category
                    )
                },
                countryOfOrigin = result.cafeDetails.coffeeBean.countryOfOrigin,
                roastingPoint = result.cafeDetails.coffeeBean.roastingPoint.toString(),
            ),
            menus = result.cafeDetails.menu.map { menu ->
                MenuResponse(
                    id = menu.id.value.toString(),
                    name = menu.name,
                    price = menu.price,
                    imageUrl = menu.imageUrl,
                    description = menu.description,
                )
            },
            tags = result.cafeDetails.tag.map { tag ->
                DetailTagResponse(
                    id = tag.id.value.toString(),
                    name = tag.name,
                    imageUrl = tag.imageUrl
                )
            },
            updatedAt = result.cafeDetails.updatedAt
        )
    }
}
