package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "tags")
class TagEntity(
    id: UUID,
    name: String,
) : BaseEntity() {

    @Id
    var id: UUID = id
        protected set

    @Column(nullable = false, unique = true)
    var name: String = name
        protected set
}
