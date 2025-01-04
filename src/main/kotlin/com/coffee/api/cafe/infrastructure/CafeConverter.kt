package com.coffee.api.cafe.infrastructure.persistence

import com.coffee.api.cafe.domain.Cafe
import com.coffee.api.cafe.infrastructure.persistence.entity.CafeEntity
import com.coffee.api.common.infrastructure.persistence.DomainEntityConverter
import org.springframework.stereotype.Component

@Component
class CafeConverter : DomainEntityConverter<Cafe, CafeEntity>(
    Cafe::class,
    CafeEntity::class,
) {
    override fun toDomain(entity: CafeEntity): Cafe {
        return Cafe(
            id = entity.id,
            value = entity.value,
        )
    }

    override fun toEntity(domain: Cafe): CafeEntity {
        return CafeEntity(
            id = domain.id.value,
            value = domain.value,
        )
    }
}