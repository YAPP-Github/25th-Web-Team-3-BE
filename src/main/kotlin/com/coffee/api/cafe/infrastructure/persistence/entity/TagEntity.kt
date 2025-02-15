package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "tags")
class TagEntity(
    id: UUID,
    name: String,
    imageUrl: String,
    priority: Float,
) : BaseEntity() {

    @Id
    var id: UUID = id
        protected set

    @Column(nullable = false, unique = true)
    var name: String = name
        protected set

    var imageUrl: String = imageUrl
        protected set

    var priority: Float = priority
        protected set
}
