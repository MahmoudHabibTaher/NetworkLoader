package com.parent.domain.auth

import com.parent.domain.base.BaseCompletableUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Completable

/**
 * Created by mahmoud on 9/8/17.
 */
class CallCheckActivationLink(threadExecutor: ThreadExecutor,
                              postThreadExecutor: PostThreadExecutor,
                              private val checkActivationLinkExpiredDataSource: AuthDataSource) :
        BaseCompletableUseCase(threadExecutor, postThreadExecutor) {
    companion object {
        const val PARAM_Link = "link"
    }

    override fun buildUseCaseCompletable(params: Params): Completable =
            checkActivationLinkExpiredDataSource.checkActivationLinkExpired(params.get(PARAM_Link))
}