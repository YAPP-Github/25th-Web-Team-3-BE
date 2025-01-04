package com.coffee.api.foo.application

import com.coffee.api.foo.application.repository.FooRepository
import com.coffee.api.foo.application.usecase.CreateFoo
import com.coffee.api.foo.domain.Foo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FooService(
    private val fooRepository: FooRepository,
) : CreateFoo {
    @Transactional
    override fun execute(input: CreateFoo.Command): CreateFoo.Result {
        val foo = fooRepository.save(Foo(input.value))
        return CreateFoo.Result(foo.id)
    }
}
