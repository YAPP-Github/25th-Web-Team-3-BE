package com.coffee.api.cafe.infrastructure.persistence

import com.coffee.api.cafe.domain.Cafe
import com.coffee.api.cafe.infrastructure.persistence.entity.CafeEntity
import com.coffee.mysql.support.DomainEntityConverter
import org.springframework.stereotype.Component

@Component
class CafeConverter : DomainEntityConverter<Cafe, CafeEntity>(
    Cafe::class,
    CafeEntity::class,
) {
    override fun toEntity(domain: Cafe): CafeEntity {
        TODO("Not yet implemented")
    }

    override fun toDomain(entity: CafeEntity): Cafe {
        TODO("Not yet implemented")
    }

}