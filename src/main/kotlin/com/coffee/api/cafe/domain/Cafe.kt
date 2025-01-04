package com.coffee.api.cafe.domain

import com.coffee.api.common.domain.AbstractDomain
import com.coffee.api.common.domain.UUIDTypeId
import com.fasterxml.jackson.annotation.JsonCreator
import java.util.*

class Cafe private constructor(
    override val id: Id,
    val value: String
) : AbstractDomain<Cafe, Cafe.Id>() {
    companion object {
        @JsonCreator
        fun create(id: UUID, value: String): Cafe {
            return Cafe(id = UUIDTypeId.from(id), value = value)
        }

        operator fun invoke(id: UUID, value: String): Cafe = Cafe.create(id, value)
    }

    data class Id(override val value: UUID) : UUIDTypeId(value)
}