package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class CafeEntity (
    @Id
    var id: UUID,

    var value: String,
) : BaseEntity()