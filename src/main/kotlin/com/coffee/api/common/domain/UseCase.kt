package com.coffee.api.common.domain

interface UseCase<in I, out O> {
    fun execute(input: I): O
}
