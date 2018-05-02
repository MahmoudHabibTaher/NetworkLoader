package com.parent.domain.builder

import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Completable

/**
 * Created by mahmoud on 12/30/17.
 */
abstract class CompletableUseCaseBuilder protected constructor(private val threadExecutor: ThreadExecutor
                                                               , private val postThreadExecutor: PostThreadExecutor) {
    protected fun buildCompletable(completable: Completable) =
            completable.subscribeOn(threadExecutor.scheduler)
                    .observeOn(postThreadExecutor.scheduler)
}