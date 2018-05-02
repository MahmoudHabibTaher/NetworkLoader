package com.parent.domain.status

import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Single

/**
 * Created by mahmoud on 9/19/17.
 */
class CallReportChildMeal(threadExecutor: ThreadExecutor,
                          postThreadExecutor: PostThreadExecutor,
                          private val statusesDataSource: StatusesDataSource,
                          private val statusStatePublisher: IStatusUpdatedPublisher) :
        BaseSingleUseCase<String>(threadExecutor, postThreadExecutor) {

    override fun buildUseCaseSingle(params: Params): Single<String> =
            statusesDataSource.reportChildrenMeal(params.get(ReportChildrenParams.PARAM_REQUEST)).doOnSuccess({
                statusStatePublisher.notifyStatusUpdated()
            })
}