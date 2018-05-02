package com.parent.domain.classes

import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.ClassChild
import com.parent.entities.ClassDetails
import com.parent.entities.ClassStaff
import com.parent.entities.Status
import io.reactivex.Single

class LoadClassStatusDetails(threadExecutor: ThreadExecutor,
                             postThreadExecutor: PostThreadExecutor,
                             private val classesDataSource: ClassesDataSource) :
        BaseSingleUseCase<ClassDetails>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<ClassDetails> {
        val classId = params.get<String>(ParamsConstants.CLASS_ID)
        val type = params.get<String>(ParamsConstants.STATUS_TYPE)
        return classesDataSource.loadClassStatusDetails(classId, type).flatMap {
            Single.just(it.let {
                ClassDetails.Builder().clone(it)
                        .currentChildren(setChildrenStatus(it.currentChildren, type))
                        .staff(setStaffStatus(it.staff, type))
                        .build()
            })
        }
    }

    private fun setChildrenStatus(children: List<ClassChild>, type: String) =
            children.map {
                ClassChild.Builder().clone(it)
                        .currentStatus(Status.Builder().type(type).build())
                        .build()
            }

    private fun setStaffStatus(staff: List<ClassStaff>, type: String) =
            staff.map {
                ClassStaff.Builder().clone(it)
                        .currentStatus(Status.Builder().type(type).build())
                        .build()
            }
}