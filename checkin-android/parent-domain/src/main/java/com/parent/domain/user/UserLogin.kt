package com.parent.domain.user

import android.app.Application
import com.parent.domain.auth.AuthDataSource
import com.parent.domain.base.BaseCompletableUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Completable

/**
 * Created by mahmoud on 9/8/17.
 */
class UserLogin(threadExecutor: ThreadExecutor,
                postThreadExecutor: PostThreadExecutor,
                private val authDataSource: AuthDataSource) :
        BaseCompletableUseCase(threadExecutor, postThreadExecutor) {
    companion object {
        const val PARAM_EMAIL = "email"
        const val PARAM_PASSWORD = "password"
    }

    override fun buildUseCaseCompletable(params: Params): Completable =
            authDataSource.login(params.get(PARAM_EMAIL), params.get(PARAM_PASSWORD))
                    .flatMapCompletable({
                        Completable.merge(mutableListOf(authDataSource.setAccessToken(it.token),
                                authDataSource.setRefreshToken(it.refreshToken),authDataSource.setLanguage(it.language)))
                    })
}