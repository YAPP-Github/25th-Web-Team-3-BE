package com.coffee.api.cafe.presentation.adapter.`in`.restapi.dto.response


data class FindAllCafesResponse(
    val cafeId: String,
    val name: String,
    val nearestStation: String,
    val location: String,
    val price: Int,
    val previewImages: List<String>?,
    val tags: List<TagResponse>
)
