package com.parent.domain.common.observers

import com.parent.domain.common.presentation.ErrorHandlerView

/**
 * Created by mahmoud on 10/27/17.
 */
class DefaultCompletableObserver(doOnComplete: () -> Unit = {},
                                 doOnError: (Throwable) -> Unit = {},
                                 private val errorHandler: ErrorHandlerView?) : BaseCompletableObserver(doOnComplete, doOnError) {
    override fun onError(e: Throwable) {
        if (errorHandler?.handleError(e) == false) {
            super.onError(e)
        }
    }
}