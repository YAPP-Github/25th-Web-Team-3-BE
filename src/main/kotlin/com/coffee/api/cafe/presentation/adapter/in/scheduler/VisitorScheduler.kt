package com.coffee.api.cafe.presentation.adapter.`in`.scheduler

import com.coffee.api.cafe.application.port.outbound.VisitorRepository
import com.coffee.api.cafe.infrastructure.persistence.entity.VisitorEntity
import org.slf4j.LoggerFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class VisitorScheduler(
    private val redisTemplate: RedisTemplate<String, String>,
    private val visitorRepository: VisitorRepository
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Scheduled(initialDelay = 3000000, fixedDelay = 3000000)
    fun updateVisitorData() {
        val keys = redisTemplate.keys("*_*") ?: return

        for (key in keys) {
            val parts = key.split("_")
            if (parts.size != 2) continue

            val userIp = parts[0]
            val date = runCatching { LocalDate.parse(parts[1]) }.getOrNull() ?: continue

            val userAgent = redisTemplate.opsForValue().get(key) ?: continue

            if (!visitorRepository.existsByUserIpAndDate(userIp, date)) {
                val visitor = VisitorEntity.of(userIp, userAgent, date)
                visitorRepository.save(visitor)
            }

            redisTemplate.delete(key)
        }
    }
}
