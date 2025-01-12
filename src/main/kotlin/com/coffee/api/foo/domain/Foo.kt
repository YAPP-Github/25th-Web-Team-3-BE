package com.coffee.api.foo.domain

import com.coffee.api.common.domain.AbstractDomain
import com.coffee.api.common.domain.UUIDTypeId
import com.fasterxml.jackson.annotation.JsonCreator
import java.util.UUID

class Foo private constructor(
    override val id: Id,
    val value: String,
) : AbstractDomain<Foo, Foo.Id>() {
    // TODO : Domain 구현시 create, invoke 강제
    companion object {
        @JsonCreator
        fun create(value: String): Foo {
            return Foo(id = UUIDTypeId.random<Id>(), value = value)
        }

        @JsonCreator
        fun create(id: UUID, value: String): Foo {
            return Foo(id = UUIDTypeId.from(id), value = value)
        }

        operator fun invoke(value: String): Foo = create(value)
        operator fun invoke(id: UUID, value: String): Foo = create(id, value)
    }

    data class Id(override val value: UUID) : UUIDTypeId(value)
}
