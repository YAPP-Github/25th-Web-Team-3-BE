package com.coffee.api.cafe.infrastructure.persistence.repository

import com.coffee.api.cafe.infrastructure.persistence.entity.CafeEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CafeJpaRepository : JpaRepository<CafeEntity, UUID>
