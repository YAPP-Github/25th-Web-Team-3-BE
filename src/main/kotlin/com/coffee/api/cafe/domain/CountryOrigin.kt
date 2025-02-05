package com.coffee.api.cafe.domain

data class CountryOrigin(
    val name: String,
    val flagImageUrl: String,
) {

    init {
        require(name.isNotBlank()) { "국가 이름은 공백일 수 없습니다." }
    }

    companion object {
        fun create(name: String, flagImageUrl: String): CountryOrigin {
            return CountryOrigin(
                name = name,
                flagImageUrl = flagImageUrl
            )
        }
    }
}
