package com.coffee.api.foo.infrastructure.repository

import com.coffee.api.foo.domain.Foo
import com.coffee.api.foo.infrastructure.repository.entity.FooEntity
import com.coffee.api.common.infrastructure.DomainEntityConverter
import org.springframework.stereotype.Component

@Component
class FooConverter : DomainEntityConverter<Foo, FooEntity>(
    Foo::class,
    FooEntity::class,
) {
    override fun toDomain(entity: FooEntity): Foo {
        return Foo(
            id = entity.id,
            value = entity.value,
        )
    }

    override fun toEntity(domain: Foo): FooEntity {
        return FooEntity(
            id = domain.id.value,
            value = domain.value,
        )
    }
}
