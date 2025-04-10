package com.coffee.api.cafe.domain

import com.coffee.api.common.domain.UUIDTypeId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.UUID

class CoffeeBeanTest {

    @DisplayName("원두 정보를 받아 원두를 생성한다")
    @Test
    fun create() {
        // given
        val id = UUID.randomUUID()
        val description = "골드문트는 독일어로 ‘황금입술’이라고 합니다. 최고급 라인업으로 분류되는 이 커피는 게이샤 품종으로, 풍부한 향과 깔끔한 맛을 특징으로 합니다."
        val cafe = createCafe()
        val name = "[골드문트] 콜롬비아 나리뇨 라 레이나 벨렌 게이샤"
        val engName = "COLOMBIA NARIÑO LA REINA BELEN GEISHA"
        val flavors = listOf(Flavor.ALMOND, Flavor.FLORAL, Flavor.APRICOT)
        val countryOfOrigin = mutableListOf(createCountryOfOrigin())
        val roastingPoint = RoastingPoint.LIGHT


        // when
        val coffeeBean = CoffeeBean(
            id = id,
            description = description,
            cafe = cafe,
            name = name,
            engName = engName,
            flavors = flavors,
            countryOfOrigin = countryOfOrigin,
            roastingPoint = roastingPoint
        )

        // then
        assertThat(coffeeBean.id).isNotNull()
        assertThat(coffeeBean)
            .extracting("name", "roastingPoint")
            .contains("[골드문트] 콜롬비아 나리뇨 라 레이나 벨렌 게이샤", RoastingPoint.LIGHT)
        assertThat(coffeeBean.flavors).hasSize(3)
            .contains(Flavor.ALMOND, Flavor.FLORAL, Flavor.APRICOT)
    }

    private fun createCountryOfOrigin(): CountryOrigin {
        val name = "콜롬비아"
        val flagImageUrl = "https://colombia.xyz"

        val countryOrigin = CountryOrigin(
                name = name,
                flagImageUrl = flagImageUrl
        )
        return countryOrigin
    }

    private fun createCafe() : Cafe {
        val id = UUID.randomUUID()
        val reasonForSelection = "최고의 커피와 분위기"
        val naverMapUrl = "https://naver.com/cafe/bluebottle"
        val name = "블루보틀"
        val nearestStation = "강남역"
        val location = "서울"
        val price = 5000
        val previewImages = listOf("img1", "img2")
        val mainImages = listOf("main1", "main2")
        val area = CafeArea.CITYHALL_GWANGHWAMUN

        val cafe = Cafe(
                id = id,
                reasonForSelection = reasonForSelection,
                naverMapUrl = naverMapUrl,
                name = name,
                nearestStation = nearestStation,
                location = location,
                price = price,
                previewImages = previewImages,
                mainImages = mainImages,
                area = area
        )

        return cafe
    }

}
