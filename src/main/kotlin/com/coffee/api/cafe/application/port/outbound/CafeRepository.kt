package com.coffee.api.cafe.application.port.outbound

import com.coffee.api.cafe.application.model.CafeDetails
import com.coffee.api.cafe.domain.Cafe
import com.coffee.api.cafe.application.model.CafePage
import java.util.UUID

interface CafeRepository {
    fun findAll(): List<Cafe>
    fun findAllCafesById(lastCafeId: UUID?, limit: Int): CafePage
    fun findByCafeId(cafeId: UUID?): CafeDetails
}
