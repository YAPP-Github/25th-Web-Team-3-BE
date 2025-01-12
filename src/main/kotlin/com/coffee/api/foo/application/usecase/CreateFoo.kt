package com.coffee.api.foo.application.usecase

import com.coffee.api.common.domain.CommandUseCase
import com.coffee.api.foo.domain.Foo

interface CreateFoo : CommandUseCase<CreateFoo.Command, CreateFoo.Result> {
    data class Command(
        val value: String,
    ) : CommandUseCase.Command

    data class Result(
        val id: Foo.Id,
    ) : CommandUseCase.Result
}
