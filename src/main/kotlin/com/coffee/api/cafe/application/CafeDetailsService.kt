package com.coffee.api.cafe.application

import com.coffee.api.cafe.application.port.inbound.FindCafeDetails
import com.coffee.api.cafe.application.port.outbound.CafeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CafeDetailsService(
    private val cafeRepository: CafeRepository,
) : FindCafeDetails {

    override fun invoke(query: FindCafeDetails.Query): FindCafeDetails.Result {
        val findByCafeId = cafeRepository.findByCafeId(query.cafeId)
        return FindCafeDetails.Result(findByCafeId)
    }
}
