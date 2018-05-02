package com.parent.domain.base

import io.reactivex.Single

/**
 * Created by mahmoud on 9/11/17.
 */
interface SingleUseCase<T> {
    fun getSingle(params: Params = emptyParams()): Single<T>
}