package com.coffee.api.cafe.application.usecase

import com.coffee.api.cafe.domain.Cafe
import com.coffee.api.common.domain.QueryUseCase
import java.util.UUID

interface FindCafe : QueryUseCase<FindCafe.Query, FindCafe.Result> {
    data class Query(
        val lastCafeId: UUID?
    ) : QueryUseCase.Query

    data class Result(
        val result: List<Cafe>,
        val hasNext: Boolean
    ) : QueryUseCase.Result
}