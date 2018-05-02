package com.parent.domain.institutions

import com.parent.domain.auth.ISessionManager
import com.parent.domain.base.BaseCompletableUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Completable

/**
 * Created by mahmoud on 12/4/17.
 */
class UpdateInstituteOptions(threadExecutor: ThreadExecutor,
                             postThreadExecutor: PostThreadExecutor,
                             private val instituteOptionsDataSource: InstituteOptionsDataSource,
                             private val sessionManager: ISessionManager) :
        BaseCompletableUseCase(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseCompletable(params: Params): Completable =
            instituteOptionsDataSource.updateInstituteOptions(params.get(ParamsConstants.INSTITUTE))
                    .andThen(sessionManager.refreshUserInfo())
}