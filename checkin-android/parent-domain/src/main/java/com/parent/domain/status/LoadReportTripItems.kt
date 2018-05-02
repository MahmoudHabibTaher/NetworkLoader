package com.parent.domain.status

import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.ClassChild
import com.parent.entities.ClassStaff
import com.parent.entities.ReportTripItem
import com.parent.entities.Status
import io.reactivex.Single

class LoadReportTripItems(threadExecutor: ThreadExecutor,
                          postThreadExecutor: PostThreadExecutor) :
        BaseSingleUseCase<List<ReportTripItem>>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<List<ReportTripItem>> =
            Single.fromCallable {
                val children = params.get<List<ClassChild>>(ReportChildrenParams.PARAM_CHILDREN)
                val staff = params.get<List<ClassStaff>>(ReportChildrenParams.PARAM_STAFF)

                val reportItems = mutableListOf<ReportTripItem>()

                reportItems.addAll(children.sortedBy { it.fullName }.map {
                    ReportTripItem.Builder()
                            .id(it.id)
                            .onTrip(it.statuses.find { it.type == Status.Type.ON_TRIP }?.let { false }
                                    ?: true)
                            .timeDisplay("")
                            .type(ReportTripItem.Type.CHILD)
                            .user(it)
                            .canReport(it.currentStatus.let {
                                it.type != Status.Type.CHILD_VACATION
                                        && it.type != Status.Type.CHILD_SICK
                            })
                            .build()
                })

                reportItems.addAll(staff.sortedBy { it.fullName }.map {
                    ReportTripItem.Builder()
                            .id(it.id)
                            .onTrip(it.statuses.find { it.type == Status.Type.ON_TRIP }?.let { false }
                                    ?: true)
                            .timeDisplay("")
                            .type(ReportTripItem.Type.STAFF)
                            .user(it)
                            .canReport(it.currentStatus.let {
                                it.type != Status.Type.CHILD_VACATION
                                        && it.type != Status.Type.CHILD_SICK
                                        && it.type != Status.Type.HAS_CHILD_SICK
                            })
                            .build()
                })

                reportItems
            }
}