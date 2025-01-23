package com.coffee.api.cafe.presentation.adapter.dto.response

data class MenuResponse(
    val id: String,
    val name : String,
    val price: Int,
    val imageUrl: String,
    val description: String,
)
