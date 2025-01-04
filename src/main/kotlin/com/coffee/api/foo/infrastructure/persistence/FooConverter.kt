package com.coffee.api.foo.infrastructure.persistence

import com.coffee.api.common.infrastructure.persistence.DomainEntityConverter
import com.coffee.api.foo.domain.Foo
import com.coffee.api.foo.infrastructure.persistence.entity.FooEntity
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
