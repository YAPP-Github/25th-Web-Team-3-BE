package com.coffee.api.cafe.infrastructure.persistence.entity

import jakarta.persistence.Embeddable

@Embeddable
data class CountryOriginEntity(
    var name: String,
    var flagImageUrl: String,
)
