package com.coffee.api.cafe.infrastructure.persistence.repository

import com.coffee.api.cafe.application.repository.CafeRepository
import com.coffee.api.cafe.domain.*
import com.coffee.api.cafe.infrastructure.CoffeeBeanConverter
import com.coffee.api.cafe.infrastructure.MenuConverter
import com.coffee.api.cafe.infrastructure.TagConverter
import com.coffee.api.cafe.infrastructure.persistence.CafeConverter
import com.coffee.api.cafe.infrastructure.persistence.entity.*
import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.support.spring.data.jpa.extension.createQuery
import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import jakarta.persistence.EntityManager
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
class CafeRepositoryImpl(
    private val kotlinJdslJpqlExecutor: KotlinJdslJpqlExecutor,
    private val cafeJpaRepository: CafeJpaRepository,
    private val cafeConverter: CafeConverter,
    private val coffeeBeanConverter: CoffeeBeanConverter,
    private val menuConverter: MenuConverter,
    private val tagConverter: TagConverter,
    private val entityManager: EntityManager,
    private val jpqlRenderContext: JpqlRenderContext
) : CafeRepository {

    override fun findAll(): List<Cafe> {
        return cafeJpaRepository.findAll()
            .map { cafeConverter.toDomain(it) }
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

        val cafes = resultList.take(limit).map { cafeConverter.toDomain(it) }
        val hasNext = resultList.size > limit
        return CafePage.from(SliceImpl(cafes, Pageable.unpaged(), hasNext))
    }


    override fun findByCafeId(cafeId: UUID?): CafeDetails {
        // UpdatedAt 조회
        val updatedAtQuery = jpql {
            select(path(CafeEntity::updatedAt))
                .from(entity(CafeEntity::class))
                .where(path(CafeEntity::id).eq(cafeId))
        }
        val updatedAt = entityManager
            .createQuery(updatedAtQuery, jpqlRenderContext)
            .resultList
            .firstOrNull()
            ?: throw IllegalArgumentException("Cafe not found for id: $cafeId")

        // CafeEntity 조회
        val cafeQuery = jpql {
            select(entity(CafeEntity::class))
                .from(entity(CafeEntity::class))
                .where(path(CafeEntity::id).eq(cafeId))
        }
        val cafeEntity = entityManager
            .createQuery(cafeQuery, jpqlRenderContext)
            .resultList
            .firstOrNull()
            ?: throw IllegalArgumentException("Cafe not found for id: $cafeId")
        val cafe = cafeConverter.toDomain(cafeEntity)

        // CoffeeBean 조회
        val coffeeBeansQuery = jpql {
            select(entity(CoffeeBeanEntity::class))
                .from(entity(CoffeeBeanEntity::class))
                .where(path(CoffeeBeanEntity::cafe).eq(cafeEntity))
        }
        val coffeeBean = entityManager
            .createQuery(coffeeBeansQuery, jpqlRenderContext)
            .resultList
            .firstOrNull()
            ?.let { coffeeBeanConverter.toDomain(it) }
            ?: throw IllegalArgumentException()

        // 메뉴 조회
        val menus = getMenusForCafe(cafeEntity)

        // 태그 조회
        val tags = getTagsForCafe(cafeEntity)

        // CafeDetails 객체 생성
        return CafeDetails.of(cafe, coffeeBean, menus, tags, updatedAt)
    }

    private fun getMenusForCafe(cafeEntity: CafeEntity): List<Menu> {
        val menuQuery = jpql {
            select(entity(MenuEntity::class))
                .from(entity(MenuEntity::class))
                .where(path(MenuEntity::cafe).eq(cafeEntity))
        }
        return entityManager
            .createQuery(menuQuery, jpqlRenderContext)
            .resultList
            .map { menuConverter.toDomain(it) }
    }

    private fun getTagsForCafe(cafeEntity: CafeEntity): List<Tag> {
        val tagQuery = jpql {
            select(entity(TagEntity::class))
                .from(
                    entity(CafeTagEntity::class),
                    leftJoin(CafeEntity::class)
                        .on(path(CafeTagEntity::cafe).eq(entity(CafeEntity::class))),
                    leftJoin(TagEntity::class)
                        .on(path(CafeTagEntity::tags).eq(entity(TagEntity::class)))
                )
                .where(path(CafeTagEntity::cafe).eq(cafeEntity))
        }

        return entityManager
            .createQuery(tagQuery, jpqlRenderContext)
            .resultList
            .map { tagConverter.toDomain(it) }
    }
}
