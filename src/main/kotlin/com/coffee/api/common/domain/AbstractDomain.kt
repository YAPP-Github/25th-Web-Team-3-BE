package com.coffee.api.common.domain

abstract class AbstractDomain<T : AbstractDomain<T, ID>, ID : TypeId<*>> : BaseDomain {

    abstract val id: ID

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AbstractDomain<*, *>

        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "${javaClass.simpleName}(id=$id)"
}
