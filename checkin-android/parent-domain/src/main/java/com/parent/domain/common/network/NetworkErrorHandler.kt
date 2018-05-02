package com.parent.domain.common.network

/**
 * Created by mahmoud on 10/12/17.
 */
interface NetworkErrorHandler {
    fun handleError(throwable: Throwable): Throwable
}