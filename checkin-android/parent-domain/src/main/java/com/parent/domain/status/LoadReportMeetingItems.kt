package com.parent.domain.status

import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.ClassStaff
import com.parent.entities.ReportMeetingItem
import com.parent.entities.Status
import io.reactivex.Single

class LoadReportMeetingItems(threadExecutor: ThreadExecutor,
                             postThreadExecutor: PostThreadExecutor) :
        BaseSingleUseCase<List<ReportMeetingItem>>(threadExecutor, postThreadExecutor) {
    override fun buildUseCaseSingle(params: Params): Single<List<ReportMeetingItem>> =
            Single.fromCallable {
                val staff = params.get<List<ClassStaff>>(ReportChildrenParams.PARAM_STAFF)

                val reportItems = mutableListOf<ReportMeetingItem>()

                reportItems.addAll(staff.sortedBy { it.fullName }.map {
                    ReportMeetingItem.Builder()
                            .id(it.id)
                            .onMeeting(it.statuses.find { it.type == Status.Type.ON_MEETING }?.let { false }
                                    ?: true)
                            .timeDisplay("")
                            .type(ReportMeetingItem.Type.STAFF)
                            .user(it)
                            .canReport(it.currentStatus.let {
                                it.type != Status.Type.CHILD_VACATION
                                        && it.type != Status.Type.CHILD_SICK
                                        && it.type != Status.Type.HAS_CHILD_SICK
                            } && it.checkedInToday)
                            .build()
                })

                reportItems
            }
}