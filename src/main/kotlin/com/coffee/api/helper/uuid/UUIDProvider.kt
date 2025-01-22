package com.coffee.api.helper.uuid

import com.github.f4b6a3.uuid.UuidCreator
import java.util.UUID

object UUIDProvider {
    fun create(): UUID {
        return UuidCreator.getTimeOrderedEpochPlus1()
    }
}
