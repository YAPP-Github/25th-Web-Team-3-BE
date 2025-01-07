package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.cafe.domain.Flavor
import com.coffee.api.cafe.domain.RoastingPoint
import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "coffee_bean")
class CoffeeBeanEntity(
    @Id
    var id: UUID,

    @Column(name = "cafe_id", nullable = false)
    var cafeId: UUID,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "image_url", nullable = false)
    var imageUrl: String,

    @Column(name = "flavor", nullable = false)
    @Enumerated(EnumType.STRING)
    var flavor: Flavor,

    @Column(name = "country_of_origin", nullable = false)
    var countryOfOrigin: String,

    @Column(name = "roasting_point", nullable = false)
    @Enumerated(EnumType.STRING)
    var roastingPoint: RoastingPoint,
) : BaseEntity()