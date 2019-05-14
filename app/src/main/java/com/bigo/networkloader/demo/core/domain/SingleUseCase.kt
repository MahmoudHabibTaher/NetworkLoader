package com.bigo.networkloader.demo.core.domain

import io.reactivex.Single

interface SingleUseCase<T, V : Params> {
    fun getSingle(params: V? = null): Single<T>
}