package com.parent.domain.user

import com.parent.domain.auth.AuthDataSource
import com.parent.domain.base.BaseCompletableUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Completable

/**
 * Created by mahmoud on 9/8/17.
 */
class CallRefreshUser(threadExecutor: ThreadExecutor,
                      postThreadExecutor: PostThreadExecutor,
                      private val authDataSource: AuthDataSource) :
        BaseCompletableUseCase(threadExecutor, postThreadExecutor) {

    override fun buildUseCaseCompletable(params: Params): Completable =
            authDataSource.getUser("").flatMapCompletable({
                Completable.complete()
            })
}