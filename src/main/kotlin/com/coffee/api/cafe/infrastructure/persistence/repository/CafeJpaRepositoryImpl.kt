package com.coffee.api.cafe.infrastructure.persistence.repository

import com.coffee.api.cafe.application.repository.CafeRepository
import com.coffee.api.cafe.domain.Cafe
import com.coffee.api.cafe.infrastructure.persistence.CafeConverter
import org.springframework.stereotype.Repository

@Repository
class CafeRepositoryImpl(
    private val cafeJpaRepository: CafeJpaRepository,
    private val converter: CafeConverter
) : CafeRepository {
    override fun findAll(): List<Cafe> {
        return cafeJpaRepository.findAll()
            .map { converter.toDomain(it) }
    }
}