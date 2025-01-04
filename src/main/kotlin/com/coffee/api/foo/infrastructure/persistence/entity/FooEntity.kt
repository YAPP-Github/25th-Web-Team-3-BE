package com.coffee.api.foo.infrastructure.persistence.entity

import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
data class FooEntity(
    @Id
    var id: UUID,

    var value: String,

    ) : BaseEntity()
