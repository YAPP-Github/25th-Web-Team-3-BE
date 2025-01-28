package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "cafe_recommend_group_mappings")
class CafeRecommendGroupMappingEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    var cafe: CafeEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    var recommendGroup: CafeRecommendGroupEntity,

    @Id
    val id: UUID = UUID.randomUUID(),
) : BaseEntity()
