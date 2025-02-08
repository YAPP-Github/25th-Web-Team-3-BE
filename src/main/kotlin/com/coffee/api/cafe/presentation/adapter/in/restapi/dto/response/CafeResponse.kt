package com.coffee.api.cafe.presentation.adapter.`in`.restapi.dto.response

data class CafeResponse(
    val id: String,
    val reasonForSelection: String,
    val naverMapUrl: String,
    val name: String,
    val nearestStation: String,
    val location: String,
    val price: Int,
    val mainImageUrl: List<String>?,
)
