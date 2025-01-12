package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.cafe.domain.Flavor
import com.coffee.api.cafe.domain.RoastingPoint
import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "coffee_beans")
class CoffeeBeanEntity(
    id: UUID,
    cafe: CafeEntity,
    name: String,
    imageUrl: String,
    flavor: Flavor,
    countryOfOrigin: String,
    roastingPoint: RoastingPoint,
) : BaseEntity() {

    @Id
    var id: UUID = id
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var cafe: CafeEntity = cafe
        protected set

    var name: String = name
        protected set

    var imageUrl: String = imageUrl
        protected set

    @Enumerated(EnumType.STRING)
    var flavor: Flavor = flavor
        protected set

    var countryOfOrigin: String = countryOfOrigin
        protected set

    @Enumerated(EnumType.STRING)
    var roastingPoint: RoastingPoint = roastingPoint
        protected set
}
