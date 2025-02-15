package com.coffee.api.cafe.domain

import com.coffee.api.common.domain.AbstractDomain
import com.coffee.api.common.domain.UUIDTypeId
import com.fasterxml.jackson.annotation.JsonCreator
import java.util.UUID

class Tag private constructor(
    override val id: Id,
    val name: String,
    val imageUrl: String,
    val priority: Float,
) : AbstractDomain<Tag, Tag.Id>() {

    companion object {
        @JsonCreator
        fun create(
            id: UUID,
            name: String,
            imageUrl: String?,
            priority: Float
        ): Tag {
            return Tag(
                id = UUIDTypeId.from(id),
                name = name,
                imageUrl = imageUrl ?: "",
                priority = priority
            )
        }

        operator fun invoke(
            id: UUID,
            name: String,
            imageUrl: String,
            priority: Float
        ): Tag = create(id, name, imageUrl, priority)
    }

    data class Id(override val value: UUID) : UUIDTypeId(value)
}
