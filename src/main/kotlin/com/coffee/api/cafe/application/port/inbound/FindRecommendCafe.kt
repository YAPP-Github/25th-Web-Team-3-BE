package com.coffee.api.cafe.application.port.inbound

import com.coffee.api.common.domain.UseCase
import java.util.*

interface FindRecommendCafe : UseCase<FindRecommendCafe.Query, FindRecommendCafe.Result> {

    data class Query(
        val lastCafeId: UUID?,
        val limit: Int,
    )

    data class Result(
        val groups: List<CafeRecommendGroup>,
        val hasNext: Boolean,
    )

    data class CafeRecommendGroup(
        val name: String,
        val cafes: List<SimpleCafe>,
    )

    data class SimpleCafe(
        val id: String,
        val mainImage: String,
        val name: String,
        val nearestStation: String,
    )
}
