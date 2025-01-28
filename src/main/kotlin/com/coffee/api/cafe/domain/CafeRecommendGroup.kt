package com.coffee.api.cafe.domain

import com.coffee.api.common.domain.AbstractDomain
import com.coffee.api.common.domain.UUIDTypeId
import java.util.*

data class CafeRecommendGroup(
    override val id: Id,
    val title: String,
    val isVisible: Boolean,
) : AbstractDomain<CafeRecommendGroup, CafeRecommendGroup.Id>() {

    companion object {
        operator fun invoke(
            id: UUID,
            title: String,
            isVisible: Boolean,
        ): CafeRecommendGroup = CafeRecommendGroup(Id(id), title, isVisible)
    }

    data class Id(override val value: UUID) : UUIDTypeId(value)
}
