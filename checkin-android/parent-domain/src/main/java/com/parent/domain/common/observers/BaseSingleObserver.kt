package com.parent.domain.common.observers

import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by mahmoud on 10/27/17.
 */
open class BaseSingleObserver<T>(private val doOnSuccess: (T) -> Unit = {},
                                 private val doOnError: (Throwable) -> Unit = {}) : DisposableSingleObserver<T>() {
    override fun onSuccess(t: T) {
        doOnSuccess(t)
    }

    override fun onError(e: Throwable) {
        doOnError(e)
    }
}