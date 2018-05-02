package com.parent.domain.base

import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Flowable

/**
 * Created by mahmoud on 9/4/17.
 */
abstract class BaseFlowableUseCase<T> protected constructor(protected val threadExecutor: ThreadExecutor
                                                            , protected val postThreadExecutor: PostThreadExecutor) : FlowableUseCase<T> {
    protected abstract fun buildUseCaseFlowable(params: Params): Flowable<T>

    override fun getFlowable(params: Params): Flowable<T> =
            buildUseCaseFlowable(params).
                    subscribeOn(threadExecutor.scheduler)
                    .observeOn(postThreadExecutor.scheduler)
}