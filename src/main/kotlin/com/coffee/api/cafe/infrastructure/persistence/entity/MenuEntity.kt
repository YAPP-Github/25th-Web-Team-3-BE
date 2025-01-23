package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.ConstraintMode
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "menus")
class MenuEntity(
    id: UUID,
    name : String,
    cafe: CafeEntity,
    price: Int,
    imageUrl: String,
    description: String,
) : BaseEntity() {

    @Id
    var id: UUID = id
        protected set

    var name: String = name
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var cafe: CafeEntity = cafe
        protected set

    var price: Int = price
        protected set

    var imageUrl: String = imageUrl
        protected set

    var description: String = description
        protected set
}
