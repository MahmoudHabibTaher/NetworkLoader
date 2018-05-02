package com.parent.domain.base.mappers

/**
 * Created by mahmoud on 11/8/17.
 */
class DefaultValueModelMapper<From, To>(private val defaultFrom: From,
                                        private val defaultTo: To) : ModelMapper<From, To> {
    override fun mapFrom(from: From): To = defaultTo

    override fun mapTo(to: To): From = defaultFrom
}