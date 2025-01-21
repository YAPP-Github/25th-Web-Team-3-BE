package com.coffee.api.cafe.application.port.inbound

import com.coffee.api.cafe.application.model.CafeDetails
import java.util.UUID

interface FindCafeDetails {

    fun invoke(query: Query): Result

    data class Query(
        val cafeId: UUID
    )

    data class Result(
        val cafeDetails: CafeDetails
    )
}
