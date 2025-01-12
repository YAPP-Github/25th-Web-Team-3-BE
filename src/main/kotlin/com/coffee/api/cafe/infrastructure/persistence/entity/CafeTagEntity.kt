package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "cafe_tags")
class CafeTagEntity(
    id: UUID,
    cafe: CafeEntity,
    tags: TagEntity,
) : BaseEntity() {

    @Id
    var id: UUID = id
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var cafe: CafeEntity = cafe
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var tags: TagEntity = tags
        protected set
}
