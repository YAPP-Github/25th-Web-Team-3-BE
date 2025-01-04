package com.coffee.api.foo.application.repository

import com.coffee.api.foo.domain.Foo
import java.util.UUID

// TODO : repository 반환타입 제한(Domain)
interface FooRepository {
    fun find(id: UUID): Foo
    fun save(foo: Foo): Foo
}
