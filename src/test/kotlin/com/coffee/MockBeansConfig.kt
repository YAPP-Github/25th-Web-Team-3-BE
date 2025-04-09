package com.coffee

import com.coffee.api.cafe.application.port.inbound.FindCafe
import com.coffee.api.cafe.application.port.inbound.FindCafeArea
import com.coffee.api.cafe.application.port.inbound.FindCafeDetails
import com.coffee.api.cafe.application.port.inbound.FindRecommendCafe
import com.coffee.api.cafe.presentation.adapter.`in`.restapi.mapper.FindAllCafesResponseMapper
import com.coffee.api.cafe.presentation.adapter.out.DiscordClientAdapter
import io.mockk.mockk
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class MockBeansConfig {

    @Bean
    fun findCafe(): FindCafe = mockk(relaxed = true)

    @Bean
    fun findCafeDetails(): FindCafeDetails = mockk(relaxed = true)

    @Bean
    fun findCafeArea(): FindCafeArea = mockk(relaxed = true)

    @Bean
    fun findRecommendCafe(): FindRecommendCafe = mockk(relaxed = true)

    @Bean
    fun discordClientAdapter(): DiscordClientAdapter = mockk(relaxed = true)

    @Bean
    fun findAllCafeResponseMapper(): FindAllCafesResponseMapper = mockk(relaxed = true)
}
