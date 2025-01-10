package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "cafes")
class CafeEntity(
    id: UUID,
    name: String,
    nearestStation: String,
    location: String,
    price: Int,
    previewImages: String,
    mainImages: String,
) : BaseEntity() {

    @Id
    var id: UUID = id
        protected set

    @Column(nullable = false)
    var name: String = name
        protected set

    var nearestStation: String = nearestStation
        protected set

    var location: String = location
        protected set

    var price: Int = price
        protected set

    var previewImages: String = previewImages
        protected set

    var mainImages: String = mainImages
        protected set
}