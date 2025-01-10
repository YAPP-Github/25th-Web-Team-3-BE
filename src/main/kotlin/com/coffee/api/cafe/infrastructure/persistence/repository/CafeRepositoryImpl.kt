package com.coffee.api.cafe.infrastructure.persistence.repository

import com.coffee.api.cafe.application.repository.CafeRepository
import com.coffee.api.cafe.domain.Cafe
import com.coffee.api.cafe.domain.CafePage
import com.coffee.api.cafe.infrastructure.persistence.CafeConverter
import com.coffee.api.cafe.infrastructure.persistence.entity.CafeEntity
import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CafeRepositoryImpl(
    private val queryFactory: SpringDataQueryFactory,
    private val cafeJpaRepository: CafeJpaRepository,
    private val converter: CafeConverter
) : CafeRepository {
    override fun findAll(): List<Cafe> {
        return cafeJpaRepository.findAll()
            .map { converter.toDomain(it) }
    }

    override fun findAllCafesById(lastCafeId: UUID?, limit: Int): CafePage {
        val listQuery = queryFactory.listQuery<CafeEntity> {
            select(entity(CafeEntity::class))
            from(entity(CafeEntity::class))
            whereAnd(
                lastCafeId?.let { column(CafeEntity::id).greaterThan(it) }
            )
            orderBy(column(CafeEntity::id).asc())
            limit(limit + 1)
        }

        val cafes = listQuery.take(limit).map { converter.toDomain(it) }
        val hasNext = listQuery.size > limit
        return CafePage.from(SliceImpl(cafes, Pageable.unpaged(), hasNext))
    }
}