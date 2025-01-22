package com.coffee.api.cafe.domain

import com.coffee.api.common.domain.AbstractDomain
import com.coffee.api.common.domain.UUIDTypeId
import com.fasterxml.jackson.annotation.JsonCreator
import java.util.UUID

class Cafe private constructor(
    override val id: Id,
    val reasonForSelection: String,
    val naverMapUrl: String,
    val name: String,
    val nearestStation: String,
    val location: String,
    val price: Int,
    val previewImages: List<String>?,
    val mainImages: List<String>?,
) : AbstractDomain<Cafe, Cafe.Id>() {
    companion object {
        @JsonCreator
        fun create(
            id: UUID,
            reasonForSelection: String,
            naverMapUrl: String,
            name: String,
            nearestStation: String,
            location: String,
            price: Int,
            previewImages: List<String>?,
            mainImages: List<String>?,
        ): Cafe {
            return Cafe(
                id = UUIDTypeId.from(id),
                reasonForSelection = reasonForSelection,
                naverMapUrl = naverMapUrl,
                name = name,
                nearestStation = nearestStation,
                location = location,
                price = price,
                previewImages = previewImages,
                mainImages = mainImages,
            )
        }

        operator fun invoke(
            id: UUID,
            reasonForSelection: String,
            naverMapUrl: String,
            name: String,
            nearestStation: String,
            location: String,
            price: Int,
            previewImages: List<String>?,
            mainImages: List<String>?,
        ): Cafe = create(id, reasonForSelection, naverMapUrl, name, nearestStation, location, price, previewImages, mainImages)
    }

    data class Id(override val value: UUID) : UUIDTypeId(value)
}
