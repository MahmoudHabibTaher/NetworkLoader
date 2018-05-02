package com.parent.domain.base

import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Single

/**
 * Created by mahmoud on 9/4/17.
 */
abstract class BaseSingleUseCase<T> protected constructor(protected val threadExecutor: ThreadExecutor
                                                          , protected val postThreadExecutor: PostThreadExecutor) : SingleUseCase<T> {
    protected abstract fun buildUseCaseSingle(params: Params): Single<T>

    override fun getSingle(params: Params): Single<T> =
            buildUseCaseSingle(params).
                    subscribeOn(threadExecutor.scheduler)
                    .observeOn(postThreadExecutor.scheduler)
}