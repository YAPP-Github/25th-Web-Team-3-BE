package com.coffee.api.common.domain

interface CommandUseCase<C : CommandUseCase.Command, R : CommandUseCase.Result> : UseCase<C, R> {
    interface Command
    interface Result
}
