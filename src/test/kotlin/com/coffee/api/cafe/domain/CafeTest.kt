package com.coffee.api.cafe.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.*

class CafeTest {

    @DisplayName("카페 정보를 받아 카페를 생성한다")
    @Test
    fun create() {
        // given
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

        // when
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

        // then
        assertThat(cafe.id).isNotNull()
        assertThat(cafe.previewImages).hasSize(2)
        assertThat(cafe)
            .extracting("location", "price")
            .contains("서울", 5000)
    }

}
