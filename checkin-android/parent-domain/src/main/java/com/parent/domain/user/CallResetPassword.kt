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
class CallResetPassword(threadExecutor: ThreadExecutor,
                        postThreadExecutor: PostThreadExecutor,
                        private val resetPassDataSource: AuthDataSource) :
        BaseCompletableUseCase(threadExecutor, postThreadExecutor) {

    companion object {
        const val PARAM_PASSWORD = "password"
        const val PARAM_TOKEN = "token"
    }

    override fun buildUseCaseCompletable(params: Params): Completable =
            resetPassDataSource.resetPassword(params.get(PARAM_PASSWORD),
                    params.get(PARAM_TOKEN)).flatMapCompletable({
                        Completable.merge(mutableListOf(
                                resetPassDataSource.setAccessToken(it.token?:""),
                                resetPassDataSource.setRefreshToken(it.refreshToken?:"")))
                    })

}