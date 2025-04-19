package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "visitors")
class VisitorEntity(
    id: UUID,
    userIp: String,
    userAgent: String,
    date: LocalDate
) : BaseEntity() {

    @Id
    var id: UUID = id
        protected set

    var userIp: String = userIp
        protected set

    var userAgent: String = userAgent
        protected set
    var date: LocalDate = date
        protected set


    companion object {
        fun of(userIp: String, userAgent: String, date: LocalDate): VisitorEntity {
            return VisitorEntity(
                id = UUID.randomUUID(),
                userIp = userIp,
                userAgent = userAgent,
                date = date
            )
        }
    }
}
