package com.coffee.api.cafe.application.repository

import com.coffee.api.cafe.domain.Cafe

interface CafeRepository {
    fun findAll() : List<Cafe>
}