package com.parent.domain.user

import com.parent.domain.base.BaseCompletableUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.domain.auth.AuthDataSource
import io.reactivex.Completable

/**
 * Created by mahmoud on 9/8/17.
 */
class SavePushNotificationToken(threadExecutor: ThreadExecutor,
                                postThreadExecutor: PostThreadExecutor,
                                private val authDataSource: AuthDataSource) :
        BaseCompletableUseCase(threadExecutor, postThreadExecutor) {
    companion object {
        const val PARAM_TOKEN = "token"
    }

    override fun buildUseCaseCompletable(params: Params): Completable =
            authDataSource.saveOneSignalToken(params.get(PARAM_TOKEN))

}