package com.coffee.api.cafe.application.usecase

import com.coffee.api.cafe.domain.Cafe
import com.coffee.api.common.domain.QueryUseCase
import java.util.*

interface FindCafeDetails : QueryUseCase<FindCafeDetails.Query, FindCafeDetails.Result> {
    data class Query(
        val cafeId: UUID?,
    ) : QueryUseCase.Query

    data class Result(
        val result: List<Cafe>, // 리턴할 값 만들어야함
    ) : QueryUseCase.Result
}
