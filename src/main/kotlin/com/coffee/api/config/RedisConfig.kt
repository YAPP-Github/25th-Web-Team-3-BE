package com.coffee.api.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import java.net.URI

@Configuration
@EnableRedisRepositories
class RedisConfig(
    @Value("\${spring.data.redis.url}")
    private val redisUrl: String
) {

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val uri = URI(redisUrl)
        return LettuceConnectionFactory(uri.host, uri.port)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<Any, Any> {
        val template = RedisTemplate<Any, Any>()
        template.connectionFactory = redisConnectionFactory()
        return template
    }
}
