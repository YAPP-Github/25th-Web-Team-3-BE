package com.coffee.api.cafe.application.port.inbound

import com.coffee.api.cafe.application.model.CafeInfoWithTags
import java.util.UUID

interface FindCafe {

    fun invoke(query: Query): Result

    data class Query(
        val lastCafeId: UUID?
    )

    data class Result(
        val cafes: List<CafeInfoWithTags>,
        val hasNext: Boolean,
    )
}
