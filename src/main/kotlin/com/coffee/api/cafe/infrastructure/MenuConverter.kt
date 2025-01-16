package com.coffee.api.cafe.infrastructure

import com.coffee.api.cafe.domain.Menu
import com.coffee.api.cafe.infrastructure.persistence.CafeConverter
import com.coffee.api.cafe.infrastructure.persistence.entity.MenuEntity
import com.coffee.api.common.infrastructure.persistence.DomainEntityConverter
import org.springframework.stereotype.Component

@Component
class MenuConverter(
    private val cafeConverter: CafeConverter
): DomainEntityConverter<Menu, MenuEntity>(
    Menu::class,
    MenuEntity::class
) {
    override fun toDomain(entity: MenuEntity): Menu {
        return Menu(
            id = entity.id,
            cafe = cafeConverter.toDomain(entity.cafe),
            price = entity.price,
            imageUrl = entity.imageUrl,
            description = entity.description
        )
    }

    override fun toEntity(domain: Menu): MenuEntity {
        return MenuEntity(
            id = domain.id.value,
            cafe = cafeConverter.toEntity(domain.cafe),
            price = domain.price,
            imageUrl = domain.imageUrl,
            description = domain.description
        )
    }
}
