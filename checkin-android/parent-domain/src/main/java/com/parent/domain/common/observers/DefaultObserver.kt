package com.parent.domain.common.observers

import com.parent.domain.common.presentation.ErrorHandlerView

/**
 * Created by mahmoud on 10/27/17.
 */
class DefaultObserver<T>(doOnNext: (T) -> Unit = {},
                         doOnComplete: () -> Unit = {},
                         doOnError: (Throwable) -> Unit = {},
                         private val errorHandler: ErrorHandlerView?) : BaseObserver<T>(doOnNext, doOnComplete, doOnError) {
    override fun onError(e: Throwable) {
        if (errorHandler?.handleError(e) == false) {
            super.onError(e)
        }
    }
}