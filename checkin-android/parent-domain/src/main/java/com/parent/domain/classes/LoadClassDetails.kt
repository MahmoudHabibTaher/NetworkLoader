package com.parent.domain.classes

import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.datetime.DateFormatter
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.ClassChild
import com.parent.entities.ClassDetails
import io.reactivex.Single

class LoadClassDetails(threadExecutor: ThreadExecutor,
                       postThreadExecutor: PostThreadExecutor,
                       private val classesDataSource: ClassesDataSource,
                       private val dateFormatter: DateFormatter) :
        BaseSingleUseCase<ClassDetails>(threadExecutor, postThreadExecutor) {

    override fun buildUseCaseSingle(params: Params): Single<ClassDetails> =
            classesDataSource.loadClass(params.get(ParamsConstants.CLASS_ID))
                    .flatMap {
                        Single.just(it.let {
                            ClassDetails.Builder().clone(it)
                                    .futureChildren(it.futureChildren.map {
                                        ClassChild.Builder().clone(it)
                                                .registrationDataDisplay(dateFormatter.formatDisplayDate(it.registrationDate))
                                                .showRegistrationDate(true)
                                                .build()
                                    })
                                    .build()
                        })
                    }
}