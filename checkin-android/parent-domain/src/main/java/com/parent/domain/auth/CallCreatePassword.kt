package com.parent.domain.auth

import com.parent.domain.base.BaseCompletableUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Completable

/**
 * Created by mahmoud on 9/8/17.
 */
class CallCreatePassword(threadExecutor: ThreadExecutor,
                         postThreadExecutor: PostThreadExecutor,
                         private val createPassDataSource: AuthDataSource) :
        BaseCompletableUseCase(threadExecutor, postThreadExecutor) {
    companion object {
        const val PARAM_PASSWORD = "password"
        const val PARAM_CONFIRMATION_CODE = "confirmation_code"
    }

    override fun buildUseCaseCompletable(params: Params): Completable =
            createPassDataSource.createPassword(params.get(PARAM_PASSWORD), params.get(PARAM_CONFIRMATION_CODE))
                    .flatMapCompletable({
                        Completable.merge(mutableListOf(createPassDataSource.setAccessToken(it.token?:""),
                                createPassDataSource.setRefreshToken(it.refreshToken?:"")))
                    })

}