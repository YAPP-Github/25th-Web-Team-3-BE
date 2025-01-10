package com.coffee.api.cafe.domain

import com.coffee.api.common.domain.AbstractDomain
import com.coffee.api.common.domain.UUIDTypeId
import com.fasterxml.jackson.annotation.JsonCreator
import java.util.*

class Cafe private constructor(
    override val id: Id,
    val name: String,
    val nearestStation: String,
    val location: String,
    val price: Int,
    val previewImages: String?,
    val mainImages: String?,
) : AbstractDomain<Cafe, Cafe.Id>() {
    companion object {
        @JsonCreator
        fun create(id: UUID,
                   name: String,
                   nearestStation: String,
                   location: String,
                   price: Int,
                   previewImages: String?,
                   mainImages: String?
        ): Cafe {
            return Cafe(
                id = UUIDTypeId.from(id),
                name = name,
                nearestStation = nearestStation,
                location = location,
                price = price,
                previewImages = previewImages,
                mainImages = mainImages
            )
        }

        operator fun invoke(
            id: UUID,
            name: String,
            nearestStation: String,
            location: String,
            price: Int,
            previewImages: String?,
            mainImages: String?
        ): Cafe = create(id, name, nearestStation, location, price, previewImages, mainImages)
    }

    data class Id(override val value: UUID) : UUIDTypeId(value)
}