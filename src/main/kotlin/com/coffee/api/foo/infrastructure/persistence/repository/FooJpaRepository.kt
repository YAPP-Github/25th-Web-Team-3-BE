package com.coffee.api.foo.infrastructure.persistence.repository

import com.coffee.api.foo.infrastructure.persistence.entity.FooEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FooJpaRepository : JpaRepository<FooEntity, UUID>
