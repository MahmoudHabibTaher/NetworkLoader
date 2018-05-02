package com.parent.domain.common.observers

import io.reactivex.observers.DisposableMaybeObserver

/**
 * Created by mahmoud on 10/27/17.
 */
open class BaseMaybeObserver<T>(private val doOnSuccess: (T) -> Unit = {},
                                private val doOnComplete: () -> Unit = {},
                                private val doOnError: (Throwable) -> Unit = {}) : DisposableMaybeObserver<T>() {
    override fun onSuccess(t: T) {
        doOnSuccess(t)
    }

    override fun onComplete() {
        doOnComplete()
    }

    override fun onError(e: Throwable) {
        doOnError(e)
    }
}