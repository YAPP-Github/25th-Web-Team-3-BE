package com.coffee.api.cafe.domain

enum class CafeArea(val displayName: String) {
    MYEONGDONG_HOEHYEON("명동성당/회현"),
    DONGDAEMUN_BANGSAN("동대문/방산"),
    CHEONGYE_JONGRO("청계/종로"),
    BUKCHON_SAMCHEONG("북촌/삼청"),
    CITYHALL_GWANGHWAMUN("시청/광화문"),
    CHUNGMURO_EULJIRO("충무로/을지로"),
    ;

    companion object {
        fun fromDisplayName(displayName: String) =
            entries.find { it.displayName == displayName }
    }
}
