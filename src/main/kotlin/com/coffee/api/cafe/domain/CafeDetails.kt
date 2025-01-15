package com.coffee.api.cafe.domain

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

class CafeDetails private constructor(
    val cafe: Cafe,
    val coffeeBean: CoffeeBean,
    val menu: List<Menu>,
    val tag: List<Tag>,
    @JsonFormat(pattern = "yyyy-MM-dd")
    val updatedAt: LocalDateTime,
){
    companion object {
        fun of(cafe: Cafe, coffeeBean: CoffeeBean, menu: List<Menu>, tag: List<Tag>, updatedAt: LocalDateTime): CafeDetails {
            return CafeDetails(
                cafe,
                coffeeBean,
                menu.toList(),
                tag.toList(),
                updatedAt
            )
        }
    }
}
