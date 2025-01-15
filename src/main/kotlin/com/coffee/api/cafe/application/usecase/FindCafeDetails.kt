package com.coffee.api.cafe.application.usecase

import com.coffee.api.cafe.domain.CafeDetails
import com.coffee.api.common.domain.QueryUseCase
import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

interface FindCafeDetails : QueryUseCase<FindCafeDetails.Query, FindCafeDetails.Result> {
    data class Query(
        val cafeId: UUID?,
    ) : QueryUseCase.Query

    @Schema(name = "Find Cafe Details", description = "카페 상세 조회 데이터")
    data class Result(
        val result: CafeDetails,
    ) : QueryUseCase.Result
}
