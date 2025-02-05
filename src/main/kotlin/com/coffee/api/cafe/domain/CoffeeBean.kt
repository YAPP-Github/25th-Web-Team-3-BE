package com.coffee.api.cafe.domain

import com.coffee.api.common.domain.AbstractDomain
import com.coffee.api.common.domain.UUIDTypeId
import com.fasterxml.jackson.annotation.JsonCreator
import java.util.UUID

class CoffeeBean private constructor(
    override val id: Id,
    val description: String,
    val cafe: Cafe,
    val name: String,
    val engName: String,
    val flavors: List<Flavor>,
    val countryOfOrigin: MutableList<CountryOrigin>,
    val roastingPoint: RoastingPoint
) : AbstractDomain<CoffeeBean, CoffeeBean.Id>() {

    companion object {
        @JsonCreator
        fun create(
            id: UUID,
            description: String,
            cafe: Cafe,
            name: String,
            engName: String,
            flavors: List<Flavor>,
            countryOfOrigin: MutableList<CountryOrigin>,
            roastingPoint: RoastingPoint
        ): CoffeeBean {
            return CoffeeBean(
                id = UUIDTypeId.from(id),
                description = description,
                cafe = cafe,
                name = name,
                engName = engName,
                flavors = flavors,
                countryOfOrigin = countryOfOrigin,
                roastingPoint = roastingPoint
            )
        }

        operator fun invoke(
            id: UUID,
            description: String,
            cafe: Cafe,
            name: String,
            engName: String,
            flavors: List<Flavor>,
            countryOfOrigin: MutableList<CountryOrigin>,
            roastingPoint: RoastingPoint
        ): CoffeeBean = create(id, description, cafe, name, engName, flavors, countryOfOrigin, roastingPoint)
    }

    data class Id(override val value: UUID) : UUIDTypeId(value)
}
