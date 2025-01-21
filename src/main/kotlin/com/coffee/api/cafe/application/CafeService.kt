package com.coffee.api.cafe.application

import com.coffee.api.cafe.application.port.inbound.FindCafe
import com.coffee.api.cafe.application.port.outbound.CafeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CafeService(
    private val cafeRepository: CafeRepository,
) : FindCafe {

    override fun invoke(query: FindCafe.Query): FindCafe.Result {
        val cafePage = cafeRepository.findAllCafesById(query.lastCafeId, 5)
        return FindCafe.Result(cafePage.cafeInfoWithTags, cafePage.hasNext)
    }
}
