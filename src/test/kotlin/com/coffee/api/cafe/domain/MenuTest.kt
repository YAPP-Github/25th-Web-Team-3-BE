package com.coffee.api.cafe.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.UUID

class MenuTest {

    @DisplayName("메뉴 정보로 메뉴를 생성합니다")
    @Test
    fun create() {
        // given
        val id = UUID.randomUUID()
        val name = "소금 커피"
        val cafe = createCafe()
        val price = 3000
        val imageUrl = "www.saltcoffee.co.kr/image"
        val description = "심심하지 않은 맛이 나는 커피"

        // when
        val menu = Menu(
            id = id,
            name = name,
            cafe = cafe,
            price = price,
            imageUrl = imageUrl,
            description = description
        )

        // then
        assertThat(menu.id).isNotNull()
        assertThat(menu.name).isEqualTo("소금 커피")

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
