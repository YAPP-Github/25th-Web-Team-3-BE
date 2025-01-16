package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.cafe.domain.Flavor
import com.coffee.api.cafe.domain.RoastingPoint
import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.ConstraintMode
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

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
