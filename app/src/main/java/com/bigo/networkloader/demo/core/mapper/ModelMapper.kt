package com.bigo.networkloader.demo.core.mapper

import io.reactivex.Single

abstract class ModelMapper<From, To> {
    abstract fun mapFrom(first: From): To

    open fun mapFrom(list: List<From>): List<To> =
        list.map { mapFrom(it) }

    open fun mapFromAsync(from: From): Single<To> =
        Single.fromCallable {
            mapFrom(from)
        }

    open fun mapFromAsync(list: List<From>): Single<List<To>> =
        Single.fromCallable {
            mapFrom(list)
        }
}