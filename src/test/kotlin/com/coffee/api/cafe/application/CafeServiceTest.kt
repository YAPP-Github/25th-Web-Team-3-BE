package com.coffee.api.cafe.application

import com.coffee.api.cafe.application.model.CafePage
import com.coffee.api.cafe.application.port.inbound.FindCafe
import com.coffee.api.cafe.application.port.outbound.CafeRepository
import com.coffee.api.cafe.domain.CafeArea
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.UUID

class CafeServiceTest {
    private val cafeRepository = mockk<CafeRepository>()
    private val cafeService = CafeService(cafeRepository)

    @Test
    @DisplayName("올바른 지역 코드로 카페를 조회하면 해당 지역의 카페 목록을 반환한다")
    fun findCafesWithValidArea() {
        // given
        val lastCafeId = UUID.randomUUID()
        val query = FindCafe.Query(
            lastCafeId = lastCafeId,
            area = CafeArea.CHEONGYE_JONGRO.name
        )

        val expectedCafePage = createCafePage(
            cafeCount = 2,
            hasNext = true
        )

        mockFindAllCafesById(
            lastCafeId = lastCafeId,
            area = CafeArea.CHEONGYE_JONGRO,
            resultPage = expectedCafePage
        )

        // when
        val result = cafeService.invoke(query)

        // then
        assertThat(result.cafes).hasSize(2)
        assertThat(result.hasNext).isTrue()
    }

    @Test
    @DisplayName("잘못된 지역 코드로 카페를 조회하면 빈 목록을 반환한다")
    fun findCafesWithInvalidArea() {
        // given
        val query = FindCafe.Query(
            lastCafeId = UUID.randomUUID(),
            area = "INVALID_AREA"
        )

        // when
        val result = cafeService.invoke(query)

        // then
        assertThat(result.cafes).isEmpty()
        assertThat(result.hasNext).isFalse()
    }

    @Test
    @DisplayName("지역 코드 없이 카페를 조회하면 전체 카페 목록을 반환한다")
    fun findCafesWithoutArea() {
        // given
        val lastCafeId = UUID.randomUUID()
        val query = FindCafe.Query(
            lastCafeId = lastCafeId,
            area = null
        )

        val expectedCafePage = createCafePage(
            cafeCount = 3,
            hasNext = false
        )

        mockFindAllCafesById(
            lastCafeId = lastCafeId,
            area = null,
            resultPage = expectedCafePage
        )

        // when
        val result = cafeService.invoke(query)

        // then
        assertThat(result.cafes).hasSize(3)
        assertThat(result.hasNext).isFalse()
    }

    private fun createCafePage(
        cafeCount: Int,
        hasNext: Boolean
    ) = CafePage(
        cafeInfoWithTags = List(cafeCount) { mockk() },
        hasNext = hasNext
    )

    private fun mockFindAllCafesById(
        lastCafeId: UUID,
        area: CafeArea?,
        resultPage: CafePage
    ) {
        every {
            cafeRepository.findAllCafesById(
                lastCafeId = lastCafeId,
                area = area,
                limit = 5
            )
        } returns resultPage
    }
}
