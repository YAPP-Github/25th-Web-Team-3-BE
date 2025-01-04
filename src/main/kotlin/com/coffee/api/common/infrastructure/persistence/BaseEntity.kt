package com.coffee.api.common.infrastructure.persistence

import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {

    @CreationTimestamp
    lateinit var createdAt: LocalDateTime

    @UpdateTimestamp
    lateinit var updatedAt: LocalDateTime

    var deletedAt: LocalDateTime? = null

    fun delete() {
        if (deletedAt != null) return
        deletedAt = LocalDateTime.now()
    }
}
