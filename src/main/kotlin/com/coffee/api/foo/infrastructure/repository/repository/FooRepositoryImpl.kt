package com.coffee.api.foo.infrastructure.repository.repository

import com.coffee.api.foo.application.repository.FooRepository
import com.coffee.api.foo.domain.Foo
import com.coffee.api.foo.infrastructure.repository.FooConverter
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class FooRepositoryImpl(
    private val fooJpaRepository: FooJpaRepository,
    private val converter: FooConverter,
) : FooRepository {
    override fun find(id: UUID): Foo {
        val entity = fooJpaRepository.findById(id).orElseThrow { RuntimeException("Not found") }
        return converter.toDomain(entity)
    }

    override fun save(foo: Foo): Foo {
        val entity = converter.toEntity(foo)
        val saved = fooJpaRepository.save(entity)
        return converter.toDomain(saved)
    }
}
