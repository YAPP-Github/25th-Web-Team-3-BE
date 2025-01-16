package com.coffee.api.cafe.application

import com.coffee.api.cafe.application.repository.CafeRepository
import com.coffee.api.cafe.application.usecase.FindCafeDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CafeDetailsService(
    private val cafeRepository: CafeRepository,
) : FindCafeDetails {

    override fun execute(input: FindCafeDetails.Query): FindCafeDetails.Result {
        val findByCafeId = cafeRepository.findByCafeId(input.cafeId)
        return FindCafeDetails.Result(findByCafeId)
    }
}
