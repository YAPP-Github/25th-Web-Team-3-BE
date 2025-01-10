package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "menus")
class MenuEntity(
    id: UUID,
    cafe: CafeEntity,
    price: Int,
    imageUrl: String,
    description: String,
) : BaseEntity() {

    @Id
    var id: UUID = id
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