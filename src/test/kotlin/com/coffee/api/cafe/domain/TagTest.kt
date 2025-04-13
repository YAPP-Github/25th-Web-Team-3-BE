package com.coffee.api.cafe.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.UUID

class TagTest {

    @DisplayName("태그 정보로 태그를 생성한다")
    @Test
    fun create() {
        // given
        val id = UUID.randomUUID()
        val name = "brand"
        val imageUrl = "www.imageUrl.xzy/image"
        val priority = 1.1f

        // when
        val tag = Tag.create(id, name, imageUrl, priority)

        // then
        assertThat(tag.id).isNotNull()
        assertThat(tag)
            .extracting("name", "imageUrl", "priority")
            .contains("brand", "www.imageUrl.xzy/image", 1.1f)
    }
}
