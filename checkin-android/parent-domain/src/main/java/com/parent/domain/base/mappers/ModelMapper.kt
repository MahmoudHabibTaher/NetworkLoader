package com.parent.domain.base.mappers

/**
 * Created by mahmoud on 9/4/17.
 */
interface ModelMapper<From, To> {
    fun mapFrom(from: From): To

    fun mapTo(to: To): From
}