package com.parent.domain.auth

import com.parent.domain.base.BaseCompletableUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Completable

/**
 * Created by mahmoud on 9/8/17.
 */
class LogUserOut(threadExecutor: ThreadExecutor,
                 postThreadExecutor: PostThreadExecutor,
                 private val authDataSource: AuthDataSource) :
        BaseCompletableUseCase(threadExecutor, postThreadExecutor) {


    override fun buildUseCaseCompletable(params: Params): Completable {
        authDataSource.deselectCurrentInstitution()
        return authDataSource.setAccessToken("")
    }
}