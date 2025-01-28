package com.coffee.api.cafe.application

import com.coffee.api.cafe.application.port.inbound.FindCafeArea
import com.coffee.api.cafe.application.port.outbound.CafeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CafeAreaService(
    private val cafeRepository: CafeRepository,
) : FindCafeArea {
    override fun execute(input: Unit): FindCafeArea.Result {
        val areas = cafeRepository.findAreas()
        return FindCafeArea.Result(
            areas.map { FindCafeArea.CafeAreaInfo(it.toString(), it.displayName) },
        )
    }
}
