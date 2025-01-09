package com.coffee.api.cafe.domain

import org.springframework.data.domain.Slice

class CafePage(
    val cafes: List<Cafe>,
    val hasNext: Boolean,
) {
    companion object {
        fun from(slice: Slice<Cafe>): CafePage {
            return CafePage(
                cafes = slice.content,
                hasNext = slice.hasNext()
            )
        }
    }
}