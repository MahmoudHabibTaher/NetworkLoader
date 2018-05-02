package com.parent.domain.common.presentation

/**
 * Created by mahmoud on 10/27/17.
 */
interface ErrorHandlerView {
    fun handleError(throwable: Throwable): Boolean
}