package com.coffee.api.cafe.presentation.adapter.dto.response

import com.coffee.api.cafe.domain.CountryOrigin

data class CoffeeBeanResponse(
    val id: String,
    val description: String,
    val name: String,
    val engName: String,
    val flavors: List<String>,
    val countryOfOrigin: MutableList<CountryOrigin>,
    val roastingPoint: String,
)
