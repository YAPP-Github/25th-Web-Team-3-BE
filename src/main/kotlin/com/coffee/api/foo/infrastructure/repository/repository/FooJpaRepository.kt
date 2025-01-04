package com.coffee.api.foo.infrastructure.repository.repository

import com.coffee.api.foo.infrastructure.repository.entity.FooEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FooJpaRepository : JpaRepository<FooEntity, UUID>
