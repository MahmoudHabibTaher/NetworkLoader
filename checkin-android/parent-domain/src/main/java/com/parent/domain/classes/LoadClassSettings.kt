package com.parent.domain.classes

import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.ClassSettings
import io.reactivex.Single

class LoadClassSettings(threadExecutor: ThreadExecutor,
                        postThreadExecutor: PostThreadExecutor,
                        private val classesDataSource: ClassesDataSource) :
        BaseSingleUseCase<ClassSettings>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<ClassSettings> =
            classesDataSource.loadClassSettings(params.get(ParamsConstants.CLASS_ID)).map {
                it.settings
            }
}