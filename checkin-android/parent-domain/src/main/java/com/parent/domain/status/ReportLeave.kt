package com.parent.domain.status

import com.parent.domain.auth.ISessionManager
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.ReportLeaveRequest
import io.reactivex.Single

/**
 * Created by habib on 3/25/18.
 */
class ReportLeave(threadExecutor: ThreadExecutor,
                  postThreadExecutor: PostThreadExecutor,
                  private val sessionManager: ISessionManager,
                  private val statusesDataSource: StatusesDataSource,
                  private val statePublisher: IStatusUpdatedPublisher) :
        BaseSingleUseCase<String>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<String> =
            params.get<ReportLeaveRequest>(ReportChildrenParams.PARAM_REPORT_LEAVE_REQUEST)
                    .let { request ->
                        sessionManager.getCurrentInstitute().flatMap {
                            statusesDataSource.reportLeave(request.children, request.staff,
                                    request.startDate, request.endDate, request.note, request.type.type,
                                    it.id).doOnSuccess {
                                statePublisher.notifyStatusUpdated()
                            }
                        }
                    }
}