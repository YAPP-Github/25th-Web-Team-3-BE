package com.coffee.api.common.domain

interface QueryUseCase<Q : QueryUseCase.Query, R : QueryUseCase.Result> : UseCase<Q, R> {
    interface Query
    interface Result
}
