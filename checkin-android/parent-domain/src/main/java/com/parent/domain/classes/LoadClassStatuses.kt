package com.parent.domain.classes

import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.Status
import io.reactivex.Single

class LoadClassStatuses(threadExecutor: ThreadExecutor,
                        postThreadExecutor: PostThreadExecutor,
                        private val classesDataSource: ClassesDataSource) :
        BaseSingleUseCase<List<Status>>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<List<Status>> =
            classesDataSource.loadClassStatuses(params.get(ParamsConstants.CLASS_ID))
}