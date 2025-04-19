package com.coffee.api.cafe.domain

import com.coffee.api.common.domain.AbstractDomain
import com.coffee.api.common.domain.UUIDTypeId
import com.fasterxml.jackson.annotation.JsonCreator
import java.time.LocalDate
import java.util.*

class Visitor private constructor(
    override val id: Id,
    val userIp: String,
    val userAgent: String,
    val date: LocalDate
) : AbstractDomain<Visitor, Visitor.Id>() {

    companion object {
        @JsonCreator
        fun create(
            id: UUID,
            userIp: String,
            userAgent: String,
            date: LocalDate
        ): Visitor {
            return Visitor(
                id = UUIDTypeId.from(id),
                userIp = userIp,
                userAgent = userAgent,
                date = date
            )
        }

        operator fun invoke(
            id: UUID,
            userIp: String,
            userAgent: String,
            date: LocalDate
        ): Visitor = create(id, userIp, userAgent, date)
    }

    data class Id(override val value: UUID) : UUIDTypeId(value)
}
