package com.coffee

import com.coffee.api.cafe.application.port.inbound.FindCafe
import com.coffee.api.cafe.application.port.inbound.FindCafeArea
import com.coffee.api.cafe.application.port.inbound.FindCafeDetails
import com.coffee.api.cafe.application.port.inbound.FindRecommendCafe
import com.coffee.api.cafe.presentation.adapter.`in`.restapi.CafeController
import com.coffee.api.cafe.presentation.adapter.`in`.restapi.mapper.FindAllCafesResponseMapper
import com.coffee.api.cafe.presentation.adapter.out.DiscordClientAdapter
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.mockk
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder

@WebMvcTest(
    controllers = [
        CafeController::class
    ]
)
@Import(MockBeansConfig::class)
abstract class ControllerTestSupport {

    @Autowired
    protected lateinit var mockMvc: MockMvc;

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @Autowired
    protected lateinit var mockMvcBuilder: MockMvcBuilder
}
