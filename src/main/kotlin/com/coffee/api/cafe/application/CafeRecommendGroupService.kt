package com.coffee.api.cafe.application

import com.coffee.api.cafe.application.port.inbound.FindRecommendCafe
import com.coffee.api.cafe.application.port.outbound.CafeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CafeRecommendGroupService(
    private val cafeRepository: CafeRepository,
) : FindRecommendCafe {
    override fun execute(input: FindRecommendCafe.Query): FindRecommendCafe.Result {
        val result = cafeRepository.findAllCafesInVisibleGroups(input.lastCafeId, input.limit)

        val groups = result.content.map {
            FindRecommendCafe.CafeRecommendGroup(
                name = it.group.title,
                cafes = it.cafes.map { cafe ->
                    FindRecommendCafe.SimpleCafe(
                        id = cafe.id.value.toString(),
                        mainImage = cafe.mainImages?.firstOrNull() ?: "",
                        name = cafe.name,
                        nearestStation = cafe.nearestStation,
                    )
                },
            )
        }

        return FindRecommendCafe.Result(
            groups = groups,
            hasNext = result.hasNext,
        )
    }
}
