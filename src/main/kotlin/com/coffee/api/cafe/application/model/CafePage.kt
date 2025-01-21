package com.coffee.api.cafe.application.model

import org.springframework.data.domain.Slice

class CafePage(
    val cafeInfoWithTags: List<CafeInfoWithTags>,
    val hasNext: Boolean,
) {
    companion object {
        fun from(slice: Slice<CafeInfoWithTags>): CafePage {
            return CafePage(
                cafeInfoWithTags = slice.content,
                hasNext = slice.hasNext(),
            )
        }
    }
}
