package com.coffee.api.cafe.infrastructure

import com.coffee.api.cafe.domain.Visitor
import com.coffee.api.cafe.infrastructure.persistence.entity.VisitorEntity
import com.coffee.api.common.infrastructure.persistence.DomainEntityConverter
import org.springframework.stereotype.Component

@Component
class VisitorConverter : DomainEntityConverter<Visitor, VisitorEntity>(
    Visitor::class,
    VisitorEntity::class
) {
    override fun toDomain(entity: VisitorEntity): Visitor {
        return Visitor(
            id = entity.id,
            userIp = entity.userIp,
            userAgent = entity.userAgent,
            date = entity.date
        )
    }

    override fun toEntity(domain: Visitor): VisitorEntity {
        return VisitorEntity(
            id = domain.id.value,
            userIp = domain.userIp,
            userAgent = domain.userAgent,
            date = domain.date
        )
    }
}
