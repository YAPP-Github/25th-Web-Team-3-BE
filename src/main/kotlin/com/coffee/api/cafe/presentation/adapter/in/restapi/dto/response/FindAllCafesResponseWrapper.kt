package com.coffee.api.cafe.presentation.adapter.`in`.restapi.dto.response

data class FindAllCafesResponseWrapper(
    val cafes: List<FindAllCafesResponse>,
    val hasNext: Boolean
)
