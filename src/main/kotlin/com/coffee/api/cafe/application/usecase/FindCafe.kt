package com.coffee.api.cafe.application.usecase

import com.coffee.api.cafe.domain.Cafe
import com.coffee.api.common.domain.QueryUseCase

interface FindCafe : QueryUseCase<FindCafe.Query, FindCafe.Result> {
    data class Query(
        val query: String
    ) : QueryUseCase.Query

    data class Result(
        val result: List<Cafe>
    ) : QueryUseCase.Result
}