package com.parent.domain.auth

import com.parent.domain.base.BaseCompletableUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Completable

/**
 * Created by mahmoud on 9/8/17.
 */
class SetUserCredentials(threadExecutor: ThreadExecutor,
                         postThreadExecutor: PostThreadExecutor,
                         private val authDataSource: AuthDataSource) :
        BaseCompletableUseCase(threadExecutor, postThreadExecutor) {
    companion object {
        const val PARAM_ACCESS_TOKEN = "access_token"
        const val PARAM_REFRESH_TOKEN = "refresh_token"
    }

    override fun buildUseCaseCompletable(params: Params): Completable =
            authDataSource.setAccessToken(params.get(PARAM_ACCESS_TOKEN))
                    .andThen { authDataSource.setRefreshToken(params.get(PARAM_REFRESH_TOKEN)) }
}