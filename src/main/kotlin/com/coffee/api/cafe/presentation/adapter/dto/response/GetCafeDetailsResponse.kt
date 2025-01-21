package com.coffee.api.cafe.presentation.adapter.dto.response

import com.coffee.api.cafe.domain.CoffeeBean
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class GetCafeDetailsResponse(
    val cafe: CafeResponse,
    val coffeeBean: CoffeeBean,
    val menus: List<MenuResponse>,
    val tags: List<TagResponse>,
    @JsonFormat(pattern = "yyyy-MM-dd")
    val updatedAt: LocalDateTime
)
