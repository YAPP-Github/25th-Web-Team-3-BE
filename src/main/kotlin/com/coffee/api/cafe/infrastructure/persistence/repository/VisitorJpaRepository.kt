package com.coffee.api.cafe.infrastructure.persistence.repository

import com.coffee.api.cafe.infrastructure.persistence.entity.VisitorEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import java.util.UUID

interface VisitorJpaRepository : JpaRepository<VisitorEntity, UUID> {
    fun existsByUserIpAndDate(userIp: String, date: LocalDate): Boolean

    fun save(visitorEntity: VisitorEntity): VisitorEntity
}
