package com.coffee.api.cafe.application

import com.coffee.api.cafe.application.port.inbound.FindCafe
import com.coffee.api.cafe.application.port.outbound.CafeRepository
import com.coffee.api.cafe.domain.CafeArea
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CafeService(
    private val cafeRepository: CafeRepository,
) : FindCafe {

    override fun invoke(query: FindCafe.Query): FindCafe.Result {
        val area = query.area?.let { code ->
            CafeArea.entries.find { it.name == code }
        }

        val cafePage = cafeRepository.findAllCafesById(
            lastCafeId = query.lastCafeId,
            area = area,
            limit = 5,
        )

        return FindCafe.Result(
            cafes = cafePage.cafeInfoWithTags,
            hasNext = cafePage.hasNext,
        )
    }
}
