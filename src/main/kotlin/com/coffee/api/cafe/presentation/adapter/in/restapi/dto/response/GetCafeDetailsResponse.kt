package com.coffee.api.cafe.presentation.adapter.`in`.restapi.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class GetCafeDetailsResponse(
    val cafe: CafeResponse,
    val coffeeBean: CoffeeBeanResponse,
    val menus: List<MenuResponse>,
    val tags: List<DetailTagResponse>,
    @JsonFormat(pattern = "yyyy-MM-dd")
    val updatedAt: LocalDateTime,
)
