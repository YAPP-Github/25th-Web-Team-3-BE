package com.coffee.api.cafe.infrastructure

import com.coffee.api.cafe.domain.CafeRecommendGroup
import com.coffee.api.cafe.infrastructure.persistence.entity.CafeRecommendGroupEntity
import com.coffee.api.common.infrastructure.persistence.DomainEntityConverter
import org.springframework.stereotype.Component

@Component
class CafeRecommendGroupConverter : DomainEntityConverter<CafeRecommendGroup, CafeRecommendGroupEntity>(
    CafeRecommendGroup::class,
    CafeRecommendGroupEntity::class,
) {
    override fun toEntity(domain: CafeRecommendGroup): CafeRecommendGroupEntity {
        return CafeRecommendGroupEntity(
            id = domain.id.value,
            title = domain.title,
            isVisible = domain.isVisible,
        )
    }

    override fun toDomain(entity: CafeRecommendGroupEntity): CafeRecommendGroup {
        return CafeRecommendGroup(
            id = entity.id,
            title = entity.title,
            isVisible = entity.isVisible,
        )
    }
}
