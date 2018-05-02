package com.parent.domain.builder

import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Single

/**
 * Created by mahmoud on 12/30/17.
 */
abstract class SingleUseCaseBuilder<T> protected constructor(private val threadExecutor: ThreadExecutor
                                                          , private val postThreadExecutor: PostThreadExecutor) {
    protected fun buildSingle(single: Single<T>) =
            single.subscribeOn(threadExecutor.scheduler)
                    .observeOn(postThreadExecutor.scheduler)
}