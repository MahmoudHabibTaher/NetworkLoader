package com.parent.domain.status

import com.parent.domain.auth.ISessionManager
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.ReportTripRequest
import io.reactivex.Single

/**
 * Created by habib on 3/26/18.
 */
class ReportTrip(threadExecutor: ThreadExecutor,
                 postThreadExecutor: PostThreadExecutor,
                 private val statusesDataSource: StatusesDataSource,
                 private val sessionManager: ISessionManager,
                 private val statusStatePublisher: IStatusUpdatedPublisher) :
        BaseSingleUseCase<String>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<String> =
            params.get<ReportTripRequest>(ReportChildrenParams.PARAM_REPORT_TRIP_REQUEST).let { request ->
                sessionManager.getCurrentInstitute().flatMap {
                    statusesDataSource.reportTrip(request, it.id)
                }.doOnSuccess({
                    statusStatePublisher.notifyStatusUpdated()
                })
            }
}