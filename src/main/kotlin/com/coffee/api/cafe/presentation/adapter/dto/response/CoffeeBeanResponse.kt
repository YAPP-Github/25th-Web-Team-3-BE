package com.coffee.api.cafe.presentation.adapter.dto.response

data class CoffeeBeanResponse(
    val id: String,
    val description: String,
    val name: String,
    val engName: String,
    val imageUrl: String,
    val flavors: List<String>,
    val countryOfOrigin: List<String>,
    val roastingPoint: String,
)
