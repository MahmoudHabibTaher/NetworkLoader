package com.parent.domain.auth

import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Single

/**
 * Created by mahmoud on 9/8/17.
 */
class IsUserLoggedIn(threadExecutor: ThreadExecutor,
                     postThreadExecutor: PostThreadExecutor,
                     private val authDataSource: AuthDataSource) :
        BaseSingleUseCase<Boolean>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<Boolean> =
            authDataSource.getAccessToken().flatMap {
                Single.just(it.isNotBlank())
            }
}