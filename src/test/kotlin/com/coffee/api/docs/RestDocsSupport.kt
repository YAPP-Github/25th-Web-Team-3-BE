package com.coffee.api.docs

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder

@ExtendWith(RestDocumentationExtension::class)
abstract class RestDocsSupport {

    protected lateinit var mockMvc: MockMvc
    protected lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp(provider: RestDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders.standaloneSetup(initController())
            .apply<StandaloneMockMvcBuilder>(documentationConfiguration(provider))
            .build()
    }

    protected abstract fun initController(): Any
}
