package com.coffee.api.cafe.infrastructure.persistence.repository

import com.coffee.api.cafe.application.repository.CafeRepository
import com.coffee.api.cafe.domain.Cafe
import com.coffee.api.cafe.domain.CafePage
import com.coffee.api.cafe.infrastructure.persistence.CafeConverter
import com.coffee.api.cafe.infrastructure.persistence.entity.CafeEntity
import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.support.spring.data.jpa.extension.createQuery
import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import jakarta.persistence.EntityManager
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CafeRepositoryImpl(
    private val kotlinJdslJpqlExecutor: KotlinJdslJpqlExecutor,
    private val cafeJpaRepository: CafeJpaRepository,
    private val converter: CafeConverter,
    private val entityManager: EntityManager,
    private val jpqlRenderContext: JpqlRenderContext
) : CafeRepository {

    override fun findAll(): List<Cafe> {
        return cafeJpaRepository.findAll()
            .map { converter.toDomain(it) }
    }

    override fun findAllCafesById(lastCafeId: UUID?, limit: Int): CafePage {
        val query = jpql {
            select(entity(CafeEntity::class))
                .from(entity(CafeEntity::class))
                .whereAnd(
                    lastCafeId?.let { path(CafeEntity::id).greaterThan(it) }
                )
                .orderBy(path(CafeEntity::id).asc())
        }

        val resultList = entityManager
            .createQuery(query, jpqlRenderContext)
            .apply { setMaxResults(limit + 1) }
            .resultList

        val cafes = resultList.take(limit).map { converter.toDomain(it) }
        val hasNext = resultList.size > limit
        return CafePage.from(SliceImpl(cafes, Pageable.unpaged(), hasNext))
    }


}
