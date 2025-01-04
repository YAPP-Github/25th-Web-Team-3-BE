package com.coffee.api.common.infrastructure

import com.coffee.api.common.domain.BaseDomain
import kotlin.reflect.KClass

abstract class DomainEntityConverter<D : BaseDomain, E : BaseEntity>(
    val domainClass: KClass<D>,
    val entityClass: KClass<E>,
) {
    abstract fun toEntity(domain: D): E
    abstract fun toDomain(entity: E): D
}
