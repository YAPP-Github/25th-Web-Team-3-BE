package com.coffee.api.common.domain

interface SliceDomain<T> {
    val content: List<T>
    val hasNext: Boolean
}
