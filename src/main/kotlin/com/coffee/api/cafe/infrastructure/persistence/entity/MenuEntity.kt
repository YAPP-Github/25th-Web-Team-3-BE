package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "menu")
class MenuEntity(
    @Id
    var id: UUID,

    @Column(name = "cafe_id")
    var cafeId: UUID,

    @Column(name = "price")
    var price: Int,

    @Column(name = "image_url")
    var imageUrl: String,

    @Column(name = "description")
    var description: String,
) : BaseEntity()