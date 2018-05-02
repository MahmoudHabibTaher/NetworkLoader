package com.parent.domain.status

import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.classes.ClassesDataSource
import com.parent.domain.classes.ParamsConstants
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.ClassModel
import io.reactivex.Single

/**
 * Created by ahmedmahmoud on 2/17/18.
 */
class LoadDashboardClasses(threadExecutor: ThreadExecutor,
                           postThreadExecutor: PostThreadExecutor,
                           private val remoteClassesDataSource: ClassesDataSource) :
        BaseSingleUseCase<List<ClassModel>>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<List<ClassModel>> =
            remoteClassesDataSource.loadDashboardClasses(params.get(ParamsConstants.INSTITUTE_ID))
}