package com.coffee.api.cafe.domain

import com.coffee.api.common.domain.AbstractDomain
import com.coffee.api.common.domain.UUIDTypeId
import com.fasterxml.jackson.annotation.JsonCreator
import java.util.UUID

class Menu private constructor(
    override val id: Id,
    val cafe: Cafe,
    val price: Int,
    val imageUrl: String,
    val description: String
) : AbstractDomain<Menu, Menu.Id>() {

    companion object {
        @JsonCreator
        fun create(
            id: UUID,
            cafe: Cafe,
            price: Int,
            imageUrl: String,
            description: String
        ): Menu {
            return Menu(
                id = UUIDTypeId.from(id),
                cafe = cafe,
                price = price,
                imageUrl = imageUrl,
                description = description
            )
        }

        operator fun invoke(
            id: UUID,
            cafe: Cafe,
            price: Int,
            imageUrl: String,
            description: String
        ): Menu = create(id, cafe, price, imageUrl, description)
    }

    data class Id(override val value: UUID) : UUIDTypeId(value)
}
