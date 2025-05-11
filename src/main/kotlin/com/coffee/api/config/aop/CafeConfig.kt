package com.coffee.api.config.aop

import io.micrometer.core.aop.CountedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CafeConfig {

    @Bean
    fun countedAspect(registry: MeterRegistry): CountedAspect =
        CountedAspect(registry)
}
