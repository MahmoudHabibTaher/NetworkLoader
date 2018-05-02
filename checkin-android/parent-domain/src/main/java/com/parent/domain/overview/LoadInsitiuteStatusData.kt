package com.parent.domain.overview

import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.classes.ParamsConstants
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.StatusData
import io.reactivex.Single

/**
 * Created by ahmedmahmoud on 2/17/18.
 */
class LoadInsitiuteStatusData(threadExecutor: ThreadExecutor,
                              postThreadExecutor: PostThreadExecutor,
                              private val remoteStatusDataSource: StatusDataDataSource) :
        BaseSingleUseCase<List<StatusData>>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<List<StatusData>> =
            remoteStatusDataSource.loadInstituteStatusData(params.get(ParamsConstants.INSTITUTE_ID), params.get(ParamsConstants.STATUS_CODE))
}