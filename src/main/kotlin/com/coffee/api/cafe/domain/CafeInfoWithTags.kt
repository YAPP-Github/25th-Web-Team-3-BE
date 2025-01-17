package com.coffee.api.cafe.domain

class CafeInfoWithTags(
    val cafeId: Cafe.Id,
    val name: String,
    val nearestStation: String,
    val location: String,
    val price: Int,
    val previewImages: String?,
    val tags: List<Tag>,
) {
    companion object {
        fun of(cafe: Cafe, tags: List<Tag>): CafeInfoWithTags {
            return CafeInfoWithTags(
                cafeId = cafe.id,
                name = cafe.name,
                nearestStation = cafe.nearestStation,
                location = cafe.location,
                price = cafe.price,
                previewImages = cafe.previewImages,
                tags = tags
            )
        }
    }
}
