package com.coffee.api.cafe.application.port.outbound

import com.coffee.api.cafe.infrastructure.persistence.entity.VisitorEntity
import java.time.LocalDate

interface VisitorRepository {

    fun existsByUserIpAndDate(userIp: String, date: LocalDate): Boolean
    fun save(visitor: VisitorEntity): VisitorEntity
}
