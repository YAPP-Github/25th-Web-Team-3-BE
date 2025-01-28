package com.coffee.api.cafe.application.port.inbound

import com.coffee.api.common.domain.UseCase

interface FindCafeArea : UseCase<Unit, FindCafeArea.Result> {

    data class Result(
        val areas: List<CafeAreaInfo>,
    )

    data class CafeAreaInfo(
        val code: String,
        val displayName: String,
    )
}
