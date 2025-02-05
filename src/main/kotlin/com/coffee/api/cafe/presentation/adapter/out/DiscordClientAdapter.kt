package com.coffee.api.cafe.presentation.adapter.out

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class DiscordClientAdapter(
    @Value("\${discord.environment}") private val environment: String,
    @Value("\${discord.webhook-url}") private val webhookUrl: String,
) {

    private val webClient: WebClient = WebClient.create()

    fun sendErrorMessage(code: String, message: String, stackTrace: String) {
        if (environment != "prod") return

        val embedData = mapOf(
            "title" to "BrewLounge 서버 에러 발생",
            "fields" to listOf(
                mapOf("name" to "발생시각", "value" to LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))),
                mapOf("name" to "에러 코드", "value" to code.toString()),
                mapOf("name" to "에러 명", "value" to message),
                mapOf("name" to "스택 트레이스", "value" to stackTrace)
            )
        )

        val payload = mapOf("embeds" to listOf(embedData))

        webClient.post()
            .uri(webhookUrl)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(payload)
            .retrieve()
            .bodyToMono<Void>()
            .block()
    }
}
