package com.coffee.api.cafe.presentation.adapter.dto.response


data class FindAllCafesResponseWrapper(
    val cafes: List<FindAllCafesResponse>,
    val hasNext: Boolean
)

data class FindAllCafesResponse(
    val cafeId: String,
    val name: String,
    val engName: String,
    val nearestStation: String,
    val location: String,
    val price: Int,
    val previewImages: String,
    val tags: List<TagResponse>
)
