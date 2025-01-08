package com.coffee.api.cafe.application.repository

import com.coffee.api.cafe.domain.Cafe
import org.springframework.data.domain.Slice
import java.util.*

interface CafeRepository {
    fun findAll() : List<Cafe>
    fun findAllCafesById(cafeId: UUID?, limit: Int): Slice<Cafe>
}