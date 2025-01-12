package com.coffee.api.config

import io.swagger.v3.oas.models.PathItem
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebMvcConfig(
    @Value("\${cors.allow-origin.urls}")
    private val allowOriginUrlPatterns: String,
) : WebMvcConfigurer {

    private val allowOriginUrls: List<String> = allowOriginUrlPatterns
        .split(",")
        .map(String::trim)

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/**")
            .allowedOriginPatterns(*allowOriginUrls.toTypedArray())
            .allowedMethods(*ALLOWED_METHODS.toTypedArray())
            .exposedHeaders("Authorization", "Set-Cookie")
            .allowCredentials(true)
            .maxAge(MAX_AGE_SECS)
    }

    companion object {
        private const val MAX_AGE_SECS: Long = 3600
        private val ALLOWED_METHODS = PathItem.HttpMethod.entries.map { it.name }
    }
}
