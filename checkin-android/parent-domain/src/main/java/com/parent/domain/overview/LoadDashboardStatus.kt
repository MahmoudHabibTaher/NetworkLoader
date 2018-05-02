package com.parent.domain.overview

import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.classes.ParamsConstants
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.Status
import io.reactivex.Single

/**
 * Created by ahmedmahmoud on 2/17/18.
 */
class LoadDashboardStatus(threadExecutor: ThreadExecutor,
                          postThreadExecutor: PostThreadExecutor,
                          private val remoteStatusDataSource: StatusDataSource) :
        BaseSingleUseCase<List<Status>>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<List<Status>> =
            remoteStatusDataSource.loadDashboadStatus(params.get(ParamsConstants.INSTITUTE_ID))
}