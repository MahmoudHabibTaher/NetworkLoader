package com.parent.domain.user.session.refresh

import com.parent.domain.auth.AuthDataSource
import com.parent.domain.base.BaseCompletableUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Completable

/**
 * Created by mahmoud on 1/22/18.
 */
class RefreshToken(threadExecutor: ThreadExecutor,
                   postThreadExecutor: PostThreadExecutor,
                   private val authDataSource: AuthDataSource) :
        BaseCompletableUseCase(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseCompletable(params: Params): Completable =
            authDataSource.refreshAccessToken("").flatMapCompletable({
                Completable.merge(mutableListOf(authDataSource.setAccessToken(it.token),
                        authDataSource.setRefreshToken(it.refreshToken)))
            })
}