package com.parent.domain.base

import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Observable

/**
 * Created by mahmoud on 6/1/17.
 */
abstract class BaseObservableUseCase<T> protected constructor(protected val threadExecutor: ThreadExecutor
                                                              , protected val postThreadExecutor: PostThreadExecutor) : ObservableUseCase<T> {
    protected abstract fun buildUseCaseObservable(params: Params): Observable<T>

    override fun getObservable(params: Params): Observable<T> =
            buildUseCaseObservable(params).
                    subscribeOn(threadExecutor.scheduler)
                    .observeOn(postThreadExecutor.scheduler)
}