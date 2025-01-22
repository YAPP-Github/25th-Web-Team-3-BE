package com.coffee.api.cafe.infrastructure

import com.coffee.api.cafe.domain.CoffeeBean
import com.coffee.api.cafe.infrastructure.persistence.CafeConverter
import com.coffee.api.cafe.infrastructure.persistence.entity.CoffeeBeanEntity
import com.coffee.api.common.infrastructure.persistence.DomainEntityConverter
import org.springframework.stereotype.Component

@Component
class CoffeeBeanConverter(
    private val cafeConverter: CafeConverter
): DomainEntityConverter<CoffeeBean, CoffeeBeanEntity>(
    CoffeeBean::class,
    CoffeeBeanEntity::class
) {
    override fun toDomain(entity: CoffeeBeanEntity): CoffeeBean {
        return CoffeeBean(
            id = entity.id,
            cafe = cafeConverter.toDomain(entity.cafe),
            name = entity.name,
            engName = entity.engName,
            imageUrl = entity.imageUrl,
            flavors = entity.flavors,
            countryOfOrigin = entity.countryOfOrigin,
            roastingPoint = entity.roastingPoint,
        )
    }

    override fun toEntity(domain: CoffeeBean): CoffeeBeanEntity {
        return CoffeeBeanEntity(
            id = domain.id.value,
            cafe = cafeConverter.toEntity(domain.cafe),
            name = domain.name,
            engName = domain.engName,
            imageUrl = domain.imageUrl,
            flavors = domain.flavors,
            countryOfOrigin = domain.countryOfOrigin,
            roastingPoint = domain.roastingPoint,
        )
    }
}
