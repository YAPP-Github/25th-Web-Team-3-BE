package com.coffee.api.cafe.presentation.adapter.`in`.restapi.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.time.LocalDate

@Component
class SingleVisitInterceptor(
    private val redisTemplate: RedisTemplate<String, String>
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val userIp = request.remoteAddr
        val userAgent = request.getHeader("User-Agent")
        val today = LocalDate.now().toString()
        val key = "${userIp}_${today}"

        val valueOperations: ValueOperations<String, String> = redisTemplate.opsForValue()

        if (!redisTemplate.hasKey(key)) {
            valueOperations.set(key, userAgent)
        }

        return true
    }
}
