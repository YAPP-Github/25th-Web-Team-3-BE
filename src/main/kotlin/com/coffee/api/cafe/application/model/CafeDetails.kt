package com.coffee.api.cafe.application.model

import com.coffee.api.cafe.domain.Cafe
import com.coffee.api.cafe.domain.CoffeeBean
import com.coffee.api.cafe.domain.Menu
import com.coffee.api.cafe.domain.Tag
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

class CafeDetails (
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
                cafe = cafe,
                coffeeBean = coffeeBean,
                menu = menu.toList(),
                tag = tag.toList(),
                updatedAt = updatedAt
            )
        }
    }
}
