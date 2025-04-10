package com.coffee.api.cafe.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CountryOriginTest {

    @DisplayName("유효한 국가 이름과 국기 이미지 URL로 원두 산지를 생성한다")
    @Test
    fun create() {
        // given
        val name = "콜롬비아"
        val flagImageUrl = "www.colombiaflag.com/images/flag"

        // when
        val countryOrigin = CountryOrigin(
            name = name,
            flagImageUrl = flagImageUrl
        )

        // then
        assertThat(countryOrigin)
            .extracting("name", "flagImageUrl")
            .contains("콜롬비아", "www.colombiaflag.com/images/flag")
    }

    @DisplayName("국가 이름이 공백이면 원두 산지 생성 시 예외가 발생한다")
    @Test
    fun createWithEmptyName() {
        // given
        val name = ""
        val flagImageUrl = "www.colombiaflag.com/images/flag"

        // when // then
        assertThatThrownBy {
            CountryOrigin(
                name = name,
                flagImageUrl = flagImageUrl
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("국가 이름은 공백일 수 없습니다.")
    }
}
