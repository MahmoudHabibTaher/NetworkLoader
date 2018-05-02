package com.parent.domain.base

import io.reactivex.Completable

/**
 * Created by mahmoud on 9/11/17.
 */
interface CompletableUseCase {
    fun getCompletable(params: Params = emptyParams()): Completable
}