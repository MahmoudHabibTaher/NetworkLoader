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
class CallCheckResetLink(threadExecutor: ThreadExecutor,
                         postThreadExecutor: PostThreadExecutor,
                         private val checkLinkExpiredDataSource: AuthDataSource) :
        BaseCompletableUseCase(threadExecutor, postThreadExecutor) {
    companion object {
        const val PARAM_Link = "link"
    }

    override fun buildUseCaseCompletable(params: Params): Completable =
            checkLinkExpiredDataSource.checkResetLinkExpired(params.get(PARAM_Link))
}