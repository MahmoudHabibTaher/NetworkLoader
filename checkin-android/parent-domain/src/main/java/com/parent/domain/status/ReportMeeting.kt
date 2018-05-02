package com.parent.domain.status

import com.parent.domain.auth.ISessionManager
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.ReportMeetingRequest
import io.reactivex.Single

/**
 * Created by habib on 3/26/18.
 */
class ReportMeeting(threadExecutor: ThreadExecutor,
                    postThreadExecutor: PostThreadExecutor,
                    private val statusesDataSource: StatusesDataSource,
                    private val sessionManager: ISessionManager,
                    private val statusStatePublisher: IStatusUpdatedPublisher) :
        BaseSingleUseCase<String>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<String> =
            params.get<ReportMeetingRequest>(ReportChildrenParams.PARAM_REPORT_MEETING_REQUEST).let { request ->
                sessionManager.getCurrentInstitute().flatMap {
                    statusesDataSource.reportMeeting(request, it.id)
                }.doOnSuccess({
                    statusStatePublisher.notifyStatusUpdated()
                })
            }
}