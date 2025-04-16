package com.coffee.api.docs.cafe

import com.coffee.api.cafe.application.model.CafeDetails
import com.coffee.api.cafe.application.model.CafeInfoWithTags
import com.coffee.api.cafe.application.port.inbound.FindCafe
import com.coffee.api.cafe.application.port.inbound.FindCafeArea
import com.coffee.api.cafe.application.port.inbound.FindCafeDetails
import com.coffee.api.cafe.application.port.inbound.FindRecommendCafe
import com.coffee.api.cafe.domain.*
import com.coffee.api.cafe.presentation.adapter.`in`.restapi.CafeController
import com.coffee.api.docs.RestDocsSupport
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class CafeControllerDocsTest : RestDocsSupport() {

    private val findCafe: FindCafe = mockk(relaxed = true)
    private val findCafeDetails: FindCafeDetails = mockk(relaxed = true)
    private val findCafeArea: FindCafeArea = mockk(relaxed = true)
    private val findRecommendCafe: FindRecommendCafe = mockk(relaxed = true)

    override fun initController(): Any {
        return CafeController(findCafe, findCafeDetails, findCafeArea, findRecommendCafe)
    }


    @DisplayName("카페 목록을 조회하는 API")
    @Test
    fun findAllCafes() {
        val sampleCafes = listOf(
            CafeInfoWithTags(
                cafeId = Cafe.Id(UUID.fromString("01949e5a-76ec-7241-a13b-c589ee0d213d")),
                name = "헤베커피",
                nearestStation = "충무로",
                location = "서울 중구 필동로 32 낙원빌딩 1층\r",
                price = 6000,
                previewImages = listOf("https://github.com/user-attachments/assets/8bb3b081-ff21-4ddd-91cf-6c43164538c5"),
                tags = listOf(
                    Tag(UUID.fromString("01949e5a-76ec-7dba-aac3-99d384a67b17"), "KBrC 수상", "", 3.2f),
                    Tag(UUID.fromString("01949e5a-76ec-7dba-aac3-99d384a67b16"), "블루리본", "", 3.2f),
                    Tag(UUID.fromString("01949e5a-76ec-7bf0-a74f-efa123d1494b"), "대중적인", "", 3.2f),
                    Tag(UUID.fromString("01949e5a-76ec-7dba-aac3-99d384a67b25"), "다크로스트", "", 3.2f)
                )
            ),
            CafeInfoWithTags(
                cafeId = Cafe.Id(UUID.fromString("01949e5a-76ec-7ce9-b945-7e74def50ab6")),
                name = "챔프커피 제3작업실",
                nearestStation = "을지로3가",
                location = "서울 중구 퇴계로36길 35 1층 섹터 커피 로스터스 본점\r",
                price = 5000,
                previewImages = listOf("https://github.com/user-attachments/assets/d3d7f4d1-6b29-4fcd-b43b-af62963655be"),
                tags = listOf(
                    Tag(UUID.fromString("01949e5a-76ec-7bf0-a74f-efa123d1494b"), "대중적인", "", 3.2f),
                    Tag(UUID.fromString("01949e5a-76ec-7dba-aac3-99d384a67b20"), "라떼 맛집", "", 3.2f),
                    Tag(UUID.fromString("01949e5a-76ec-7dba-aac3-99d384a67b15"), "블렌드", "", 3.2f),
                    Tag(UUID.fromString("01949e5a-76ec-7dba-aac3-99d384a67b25"), "다크로스트", "", 3.2f)
                )
            )
            // 필요 시 나머지 카페들도 추가 가능
        )

        val result = FindCafe.Result(
            cafes = sampleCafes,
            hasNext = true
        )

        every { findCafe.invoke(any()) } returns result

        mockMvc.perform(
            get("/api/v1/cafes")
                .param("lastCafeId", "123e4567-e89b-12d3-a456-556642440000")
                .param("area", "HONGDAE")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("SUCCESS"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.hasNext").isBoolean())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.cafes").isArray())
            .andDo(
                document(
                    "cafe-findAll",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    queryParameters(
                        parameterWithName("lastCafeId").description("마지막으로 조회된 카페 ID (UUID)").optional(),
                        parameterWithName("area").description("조회할 지역 (예: HONGDAE, GANGNAM)")
                    ),
                    responseFields(
                        fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과 상태 (예: SUCCESS)"),
                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("카페 목록 응답 데이터"),
                        fieldWithPath("data.hasNext").type(JsonFieldType.BOOLEAN).description("다음 페이지 존재 여부"),
                        fieldWithPath("data.cafes").type(JsonFieldType.ARRAY).description("카페 목록"),

                        fieldWithPath("data.cafes[].cafeId").type(JsonFieldType.STRING).description("카페 ID (UUID)"),
                        fieldWithPath("data.cafes[].name").type(JsonFieldType.STRING).description("카페 이름"),
                        fieldWithPath("data.cafes[].nearestStation").type(JsonFieldType.STRING).description("가장 가까운 지하철역"),
                        fieldWithPath("data.cafes[].location").type(JsonFieldType.STRING).description("카페 위치"),
                        fieldWithPath("data.cafes[].price").type(JsonFieldType.NUMBER).description("기본 가격대"),
                        fieldWithPath("data.cafes[].previewImages").type(JsonFieldType.ARRAY).optional().description("카페 미리보기 이미지 URL 목록"),
                        fieldWithPath("data.cafes[].tags").type(JsonFieldType.ARRAY).description("카페 관련 태그"),

                        fieldWithPath("data.cafes[].tags[].id").type(JsonFieldType.STRING).description("태그 ID"),
                        fieldWithPath("data.cafes[].tags[].name").type(JsonFieldType.STRING).description("태그 이름"),
                        fieldWithPath("error").type(JsonFieldType.NULL).description("에러 메시지 (성공 시 null)")
                    )
                )
            )
    }

    @DisplayName("카페 상세 정보를 조회하는 API")
    @Test
    fun getCafeDetails() {
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
            menu = emptyList(),
            tag = emptyList(),
            updatedAt = now
        )

        val result = FindCafeDetails.Result(dummyCafeDetails)

        every { findCafeDetails.invoke(any()) } returns result

        mockMvc.perform(
            get("/api/v1/cafes/details/{cafeId}", "123e4567-e89b-12d3-a456-556642440000")
                .param("cafeId", "123e4567-e89b-12d3-a456-556642440000")
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("SUCCESS"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.cafe.name").value("텐스퀘어 남산"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.coffeeBean.name").value("Test CoffeeBean"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.updatedAt").value(now.format(formatter)))
            .andDo(
                document(
                    "cafe-details",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    pathParameters(
                        parameterWithName("cafeId").description("조회할 카페의 ID (UUID)")
                    ),
                    responseFields(
                        fieldWithPath("result").type(JsonFieldType.STRING).description("응답 결과 상태 (예: SUCCESS)"),
                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("카페 상세 정보 데이터"),

                        fieldWithPath("data.cafe").type(JsonFieldType.OBJECT).description("카페 정보"),
                        fieldWithPath("data.cafe.id").type(JsonFieldType.STRING).description("카페 ID"),
                        fieldWithPath("data.cafe.name").type(JsonFieldType.STRING).description("카페 이름"),
                        fieldWithPath("data.cafe.nearestStation").type(JsonFieldType.STRING).description("가장 가까운 지하철역"),
                        fieldWithPath("data.cafe.location").type(JsonFieldType.STRING).description("카페 위치"),
                        fieldWithPath("data.cafe.price").type(JsonFieldType.NUMBER).description("기본 가격대"),
                        fieldWithPath("data.cafe.mainImageUrl").type(JsonFieldType.ARRAY).description("카페 메인 이미지 목록"),
                        fieldWithPath("data.cafe.reasonForSelection").type(JsonFieldType.STRING).description("카페 선정 이유"),
                        fieldWithPath("data.cafe.naverMapUrl").type(JsonFieldType.STRING).description("네이버 지도 URL"),

                        fieldWithPath("data.coffeeBean").type(JsonFieldType.OBJECT).description("사용 중인 원두 정보"),
                        fieldWithPath("data.coffeeBean.id").type(JsonFieldType.STRING).description("원두 ID"),
                        fieldWithPath("data.coffeeBean.name").type(JsonFieldType.STRING).description("원두 이름"),
                        fieldWithPath("data.coffeeBean.engName").type(JsonFieldType.STRING).description("원두 영어 이름"),
                        fieldWithPath("data.coffeeBean.description").type(JsonFieldType.STRING).description("원두 설명"),
                        fieldWithPath("data.coffeeBean.flavors").type(JsonFieldType.ARRAY).description("원두 맛 노트"),
                        fieldWithPath("data.coffeeBean.countryOfOrigin").type(JsonFieldType.ARRAY).description("원두 산지"),
                        fieldWithPath("data.coffeeBean.roastingPoint").type(JsonFieldType.STRING).description("로스팅 포인트"),

                        fieldWithPath("data.menus").type(JsonFieldType.ARRAY).description("카페 메뉴"),
                        fieldWithPath("data.tags").type(JsonFieldType.ARRAY).description("카페 태그 목록"),

                        fieldWithPath("data.updatedAt").type(JsonFieldType.STRING).description("정보 업데이트 날짜"),
                        fieldWithPath("error").type(JsonFieldType.NULL).description("에러 메시지 (성공 시 null)")
                    )
                )
            )
    }
}
