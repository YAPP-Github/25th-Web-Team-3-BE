package com.coffee.api.cafe.application.port.outbound.model

import com.coffee.api.cafe.domain.Cafe
import com.coffee.api.cafe.domain.CafeRecommendGroup
import com.coffee.api.common.domain.SliceDomain

data class CafeInfoWithRecommendGroups(
    override val content: List<GroupedCafes>,
    override val hasNext: Boolean,
) : SliceDomain<CafeInfoWithRecommendGroups.GroupedCafes> {

    data class GroupedCafes(
        val group: CafeRecommendGroup,
        val cafes: List<Cafe>,
    )
}
