package com.coffee.api.cafe.application.port.outbound

import com.coffee.api.cafe.application.model.CafeDetails
import com.coffee.api.cafe.application.model.CafePage
import com.coffee.api.cafe.domain.Cafe
import com.coffee.api.cafe.domain.CafeArea
import com.coffee.api.cafe.application.port.outbound.model.CafeInfoWithRecommendGroups
import com.coffee.api.cafe.domain.Cafe
import java.util.UUID

interface CafeRepository {
    fun findAll(): List<Cafe>
    fun findAllCafesById(lastCafeId: UUID?, area: CafeArea?, limit: Int): CafePage
    fun findByCafeId(cafeId: UUID?): CafeDetails
    fun findAreas(): List<CafeArea>
    fun findAllCafesInVisibleGroups(lastCafeId: UUID?, limit: Int): CafeInfoWithRecommendGroups
}
