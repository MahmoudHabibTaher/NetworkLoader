package com.bigo.networkloader.demo.core.domain

import com.bigo.networkloader.demo.core.domain.executors.ThreadExecutor
import io.reactivex.Single

abstract class BaseSingleUseCase<T, V : Params>(
    protected val threadExecutor: ThreadExecutor,
    protected val postThreadExecutor: ThreadExecutor
) : SingleUseCase<T, V> {
    abstract fun buildUseCase(params: V?): Single<T>

    override fun getSingle(params: V?): Single<T> =
        buildUseCase(params)
            .subscribeOn(threadExecutor.scheduler())
            .observeOn(postThreadExecutor.scheduler())
}