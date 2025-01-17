package com.coffee.api.cafe.application.usecase

import com.coffee.api.cafe.domain.CafeInfoWithTags
import com.coffee.api.common.domain.QueryUseCase
import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

interface FindCafe : QueryUseCase<FindCafe.Query, FindCafe.Result> {
    data class Query(
        val lastCafeId: UUID?,
    ) : QueryUseCase.Query

    @Schema(name = "Find All Cafe", description = "모든 카페 리스트")
    data class Result(
        val result: List<CafeInfoWithTags>,
        val hasNext: Boolean,
    ) : QueryUseCase.Result
}
