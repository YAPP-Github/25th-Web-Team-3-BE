package com.coffee.api.cafe.infrastructure.persistence.repository

import com.coffee.api.cafe.application.port.outbound.VisitorRepository
import com.coffee.api.cafe.infrastructure.persistence.entity.VisitorEntity
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class VisitorRepositoryImpl(
    private val visitorJpaRepository: VisitorJpaRepository
) : VisitorRepository {
    override fun existsByUserIpAndDate(userIp: String, date: LocalDate): Boolean {
        return visitorJpaRepository.existsByUserIpAndDate(userIp, date)
    }

    override fun save(visitor: VisitorEntity): VisitorEntity {
        return visitorJpaRepository.save(visitor)
    }
}
