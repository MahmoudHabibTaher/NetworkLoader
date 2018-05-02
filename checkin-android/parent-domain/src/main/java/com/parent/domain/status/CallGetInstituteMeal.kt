package com.parent.domain.status

import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.InstituteMeal
import io.reactivex.Single

/**
 * Created by mahmoud on 9/19/17.
 */
class CallGetInstituteMeal(threadExecutor: ThreadExecutor,
                           postThreadExecutor: PostThreadExecutor,
                           private val statusesDataSource: StatusesDataSource) :
        BaseSingleUseCase<List<InstituteMeal>>(threadExecutor, postThreadExecutor) {

    override fun buildUseCaseSingle(params: Params): Single<List<InstituteMeal>> =
            statusesDataSource.getInstitutionMeal(params.get(ReportChildrenParams.PARAM_INST_ID))
}