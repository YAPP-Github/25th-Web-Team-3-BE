package com.coffee.api.cafe.presentation.adapter.`in`.restapi

import com.coffee.ControllerTestSupport
import com.coffee.api.cafe.application.model.CafeDetails
import com.coffee.api.cafe.application.port.inbound.FindCafe
import com.coffee.api.cafe.application.port.inbound.FindCafeDetails
import com.coffee.api.cafe.domain.Cafe
import com.coffee.api.cafe.domain.CafeArea
import com.coffee.api.cafe.domain.CoffeeBean
import com.coffee.api.cafe.domain.RoastingPoint
import io.mockk.every
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

class CafeControllerTest : ControllerTestSupport() {

    @Autowired
    lateinit var findCafe: FindCafe

    @Autowired
    lateinit var findCafeDetails: FindCafeDetails

    @DisplayName("카페 목록을 조회한다.")
    @Test
    fun findAllCafes() {
        // given
        val result = FindCafe.Result(
            emptyList(),
            false
        )

        // stubbing
        every { findCafe.invoke(any()) } returns result

        // when // then
        mockMvc.perform(
            get("/api/v1/cafes")
                .param("lastCafeId", "123e4567-e89b-12d3-a456-556642440000")
                .param("area", "HONGDAE")
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.result").value("SUCCESS"))
            .andExpect(jsonPath("$.data.hasNext").isBoolean())
            .andExpect(jsonPath("$.data.cafes").isArray())
    }

    @DisplayName("카페 상세 정보를 조회한다.")
    @Test
    fun getCafeDetails() {
        // given
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val dummyCafe = Cafe.create(
            id = UUID.fromString("123e4567-e89b-12d3-a456-556642440000"),
            reasonForSelection = "스몰토크를 좋아하는 사장님은 지역사회에 따뜻한 온기를 불어넣습니다. 스피드스터 커피머신과 이쪼 발키리를 사용하며. 게이샤의 맛을 알리는데 일조합니다.",
            naverMapUrl = "http://naver.com",
            name = "텐스퀘어 남산",
            nearestStation = "Test Station",
            location = "Test Location",
            price = 1000,
            previewImages = listOf("preview1.jpg", "preview2.jpg"),
            mainImages = listOf("main1.jpg"),
            area = CafeArea.CHEONGYE_JONGRO
        )

        val dummyCoffeeBean = CoffeeBean.create(
            id = UUID.fromString("223e4567-e89b-12d3-a456-556642440000"),
            description = "Rich and smooth coffee bean",
            cafe = dummyCafe,
            name = "Test CoffeeBean",
            engName = "Test CoffeeBean",
            flavors = emptyList(),
            countryOfOrigin = mutableListOf(),
            roastingPoint = RoastingPoint.MEDIUM
        )

        val dummyCafeDetails = CafeDetails(
            cafe = dummyCafe,
            coffeeBean = dummyCoffeeBean,
            menu = emptyList(),  // 메뉴 데이터가 있다면 추가
            tag = emptyList(),   // 태그 데이터가 있다면 추가
            updatedAt = now
        )

        val result = FindCafeDetails.Result(dummyCafeDetails)

        every { findCafeDetails.invoke(any()) } returns result

        // when // then
        mockMvc.perform(
            get("/api/v1/cafes/details/{cafeId}", "123e4567-e89b-12d3-a456-556642440000")
                .param("cafeId", "123e4567-e89b-12d3-a456-556642440000")
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.result").value("SUCCESS"))
            .andExpect(jsonPath("$.data.cafe.name").value("텐스퀘어 남산"))
            .andExpect(jsonPath("$.data.coffeeBean.name").value("Test CoffeeBean"))
            .andExpect(jsonPath("$.data.updatedAt").value(now.format(formatter)))
    }

}
