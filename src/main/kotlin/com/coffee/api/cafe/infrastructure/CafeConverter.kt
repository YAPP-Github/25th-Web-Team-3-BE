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
            reasonForSelection = entity.reasonForSelection,
            naverMapUrl = entity.naverMapUrl,
            name = entity.name,
            nearestStation = entity.nearestStation,
            location = entity.location,
            price = entity.price,
            previewImages = entity.previewImages,
            mainImages = entity.mainImages,
            area = entity.area,
        )
    }

    override fun toEntity(domain: Cafe): CafeEntity {
        return CafeEntity(
            id = domain.id.value,
            reasonForSelection = domain.reasonForSelection,
            naverMapUrl = domain.naverMapUrl,
            name = domain.name,
            nearestStation = domain.nearestStation,
            location = domain.location,
            price = domain.price,
            previewImages = domain.previewImages ?: listOf(),
            mainImages = domain.mainImages ?: listOf(),
            area = domain.area,
        )
    }
}
