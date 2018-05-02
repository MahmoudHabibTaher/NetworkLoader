package com.parent.domain.classes

import com.parent.entities.ClassModel
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Single

/**
 * Created by mahmoud on 12/6/17.
 */
class LoadClasses(threadExecutor: ThreadExecutor,
                  postThreadExecutor: PostThreadExecutor,
                  private val classesDataSource: ClassesDataSource) :
        BaseSingleUseCase<List<ClassModel>>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<List<ClassModel>> =
            classesDataSource.loadClasses(params.get(ParamsConstants.INSTITUTE_ID))
}