package com.coffee.api.cafe.infrastructure.repository.repository

import com.coffee.api.cafe.application.repository.CafeRepository

class CafeRepositoryImpl(
    private val cafeJpaRepository: CafeJpaRepository,
    private val converter: CafeConverter
) : CafeRepository {
}