package com.coffee.api.cafe.domain

import com.coffee.api.common.domain.AbstractDomain
import com.coffee.api.common.domain.UUIDTypeId
import com.fasterxml.jackson.annotation.JsonCreator
import java.util.UUID

class CoffeeBean private constructor(
    override val id: Id,
    val cafe: Cafe,
    val name: String,
    val engName: String,
    val imageUrl: String,
    val flavor: Flavor,
    val countryOfOrigin: String,
    val roastingPoint: RoastingPoint
) : AbstractDomain<CoffeeBean, CoffeeBean.Id>() {

    companion object {
        @JsonCreator
        fun create(
            id: UUID,
            cafe: Cafe,
            name: String,
            engName: String,
            imageUrl: String,
            flavor: Flavor,
            countryOfOrigin: String,
            roastingPoint: RoastingPoint
        ): CoffeeBean {
            return CoffeeBean(
                id = UUIDTypeId.from(id),
                cafe = cafe,
                name = name,
                engName = engName,
                imageUrl = imageUrl,
                flavor = flavor,
                countryOfOrigin = countryOfOrigin,
                roastingPoint = roastingPoint
            )
        }

        operator fun invoke(
            id: UUID,
            cafe: Cafe,
            name: String,
            engName: String,
            imageUrl: String,
            flavor: Flavor,
            countryOfOrigin: String,
            roastingPoint: RoastingPoint
        ): CoffeeBean = create(id, cafe, name, engName, imageUrl, flavor, countryOfOrigin, roastingPoint)
    }

    data class Id(override val value: UUID) : UUIDTypeId(value)
}
