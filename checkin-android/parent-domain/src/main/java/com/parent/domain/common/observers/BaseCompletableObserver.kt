package com.parent.domain.common.observers

import io.reactivex.observers.DisposableCompletableObserver

/**
 * Created by mahmoud on 10/27/17.
 */
open class BaseCompletableObserver(private val doOnComplete: () -> Unit = {},
                                   private val doOnErrror: (Throwable) -> Unit = {}) : DisposableCompletableObserver() {
    override fun onComplete() {
        doOnComplete()
    }

    override fun onError(e: Throwable) {
        doOnErrror(e)
    }
}