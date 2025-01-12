package com.coffee.api.cafe.application.repository

import com.coffee.api.cafe.domain.Cafe
import com.coffee.api.cafe.domain.CafePage
import java.util.*

interface CafeRepository {
    fun findAll(): List<Cafe>
    fun findAllCafesById(lastCafeId: UUID?, limit: Int): CafePage
}
