package com.coffee.api.cafe.infrastructure.persistence.repository

import com.coffee.api.cafe.application.model.CafeDetails
import com.coffee.api.cafe.application.model.CafeInfoWithTags
import com.coffee.api.cafe.application.model.CafePage
import com.coffee.api.cafe.application.port.outbound.CafeRepository
import com.coffee.api.cafe.application.port.outbound.model.CafeInfoWithRecommendGroups
import com.coffee.api.cafe.domain.*
import com.coffee.api.cafe.infrastructure.CafeRecommendGroupConverter
import com.coffee.api.cafe.infrastructure.CoffeeBeanConverter
import com.coffee.api.cafe.infrastructure.MenuConverter
import com.coffee.api.cafe.infrastructure.TagConverter
import com.coffee.api.cafe.infrastructure.persistence.CafeConverter
import com.coffee.api.cafe.infrastructure.persistence.entity.*
import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.support.spring.data.jpa.extension.createQuery
import jakarta.persistence.EntityManager
import jakarta.persistence.Tuple
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.SliceImpl
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class CafeRepositoryImpl(
    private val cafeJpaRepository: CafeJpaRepository,
    private val cafeConverter: CafeConverter,
    private val coffeeBeanConverter: CoffeeBeanConverter,
    private val menuConverter: MenuConverter,
    private val tagConverter: TagConverter,
    private val entityManager: EntityManager,
    private val jpqlRenderContext: JpqlRenderContext,
    private val cafeRecommendGroupConverter: CafeRecommendGroupConverter,
) : CafeRepository {

    override fun findAll(): List<Cafe> {
        return cafeJpaRepository.findAll()
            .map { cafeConverter.toDomain(it) }
    }

    override fun findAllCafesById(lastCafeId: UUID?, limit: Int): CafePage {
        val query = jpql {
            select<Tuple>(entity(CafeEntity::class), entity(TagEntity::class))
                .from(
                    entity(CafeEntity::class),
                    leftFetchJoin(CafeTagEntity::class)
                        .on(entity(CafeEntity::class).eq(path(CafeTagEntity::cafe))),
                    leftFetchJoin(TagEntity::class)
                        .on(entity(TagEntity::class).eq(path(CafeTagEntity::tags))),
                )
                .whereAnd(
                    lastCafeId?.let {
                        path(CafeEntity::id).greaterThan(it)
                    },
                    path(CafeEntity::deletedAt).isNull()
                )
                .orderBy(path(CafeEntity::id).asc())
        }

        val resultList = entityManager
            .createQuery(query, jpqlRenderContext)
            .resultList

        val cafesWithTags = resultList.groupBy(
            { tuple -> tuple.get(0, CafeEntity::class.java) },
            { tuple -> tuple.get(1, TagEntity::class.java) }
        ).map { (cafeEntity, tagEntities) ->
            val cafe = cafeConverter.toDomain(cafeEntity)
            val tags = tagEntities.filterNotNull()
                .map { tagConverter.toDomain(it) }

            CafeInfoWithTags.of(cafe, tags)
        }.take(limit)

        val hasNext = resultList.size > limit
        return CafePage.from(SliceImpl(
                cafesWithTags,
                Pageable.unpaged(), hasNext
            ))
    }

    override fun findByCafeId(cafeId: UUID?): CafeDetails {
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

        // updatedAt 조회
        val updatedAt = cafeEntity.updatedAt

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
            ?: throw IllegalArgumentException("CoffeeBean not found for cafe: $cafeId")

        // 메뉴 조회
        val menus = getMenusForCafe(cafeEntity)

        // 태그 조회
        val tags = getTagsForCafe(cafeEntity)

        // CafeDetails 객체 생성
        return CafeDetails.of(cafe, coffeeBean, menus, tags, updatedAt)
    }

    override fun findAllCafesInVisibleGroups(lastGroupId: UUID?, limit: Int): CafeInfoWithRecommendGroups {
        val query = jpql {
            select<Tuple>(
                entity(CafeEntity::class),
                entity(CafeRecommendGroupEntity::class),
            )
                .from(
                    entity(CafeEntity::class),
                    leftFetchJoin(CafeRecommendGroupMappingEntity::class)
                        .on(entity(CafeEntity::class).eq(path(CafeRecommendGroupMappingEntity::cafe))),
                    leftFetchJoin(CafeRecommendGroupEntity::class)
                        .on(
                            entity(
                                CafeRecommendGroupEntity::class,
                            ).eq(path(CafeRecommendGroupMappingEntity::recommendGroup)),
                        ),
                )
                .whereAnd(
                    path(CafeRecommendGroupEntity::isVisible).eq(true),
                    path(CafeEntity::deletedAt).isNull(),
                    lastGroupId?.let {
                        path(CafeRecommendGroupEntity::id).greaterThan(it)
                    },
                )
                .orderBy(path(CafeRecommendGroupEntity::id).asc())
        }

        val queryResult = entityManager
            .createQuery(query, jpqlRenderContext)
            .setMaxResults(limit + 1)
            .resultList

        val groupedResults = queryResult.groupBy(
            { tuple -> tuple.get(1, CafeRecommendGroupEntity::class.java) },
            { tuple -> tuple.get(0, CafeEntity::class.java) },
        )

        val groups = groupedResults.map { (groupEntity, cafeEntities) ->
            CafeInfoWithRecommendGroups.GroupedCafes(
                group = cafeRecommendGroupConverter.toDomain(groupEntity),
                cafes = cafeEntities.filterNotNull().map { cafeConverter.toDomain(it) },
            )
        }

        val hasNext = queryResult.size > limit
        val slicedGroups = if (hasNext) groups.dropLast(1) else groups

        return CafeInfoWithRecommendGroups(
            slicedGroups,
            hasNext,
        )
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
                    leftFetchJoin(CafeEntity::class)
                        .on(path(CafeTagEntity::cafe).eq(entity(CafeEntity::class))),
                    leftFetchJoin(TagEntity::class)
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
