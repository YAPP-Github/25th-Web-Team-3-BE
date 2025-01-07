package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "cafe_tag")
class CafeTagEntity(
    @Id
    var id: UUID,

    @Column(name = "cafe_id", nullable = false)
    var cafeId: UUID,

    @Column(name = "tag_id", nullable = false)
    var tagId: UUID,
) : BaseEntity()