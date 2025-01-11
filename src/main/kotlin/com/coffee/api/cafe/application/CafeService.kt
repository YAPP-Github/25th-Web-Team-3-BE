package com.coffee.api.cafe.application

import com.coffee.api.cafe.application.repository.CafeRepository
import com.coffee.api.cafe.application.usecase.FindCafe
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CafeService(
    private val cafeRepository: CafeRepository,
) : FindCafe {

    override fun execute(input: FindCafe.Query): FindCafe.Result {
        val findAllCafesById = cafeRepository.findAllCafesById(input.lastCafeId, 5)
        return FindCafe.Result(findAllCafesById.cafes, findAllCafesById.hasNext)
    }
}