package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "cafe_recommend_groups")
class CafeRecommendGroupEntity(
    @Id
    val id: UUID,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var isVisible: Boolean = true,
) : BaseEntity()
