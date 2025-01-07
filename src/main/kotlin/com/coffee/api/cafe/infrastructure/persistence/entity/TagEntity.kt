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
    @Id
    var id: UUID,

    @Column(nullable = false, unique = true)
    var name: String,
) : BaseEntity()