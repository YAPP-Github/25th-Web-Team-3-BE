package com.coffee.api.foo.infrastructure.repository.entity

import com.coffee.api.common.infrastructure.BaseEntity
import jakarta.persistence.Entity

import jakarta.persistence.Id
import java.util.UUID

@Entity
data class FooEntity(
    @Id
    var id: UUID,

    var value: String,

    ) : BaseEntity()
