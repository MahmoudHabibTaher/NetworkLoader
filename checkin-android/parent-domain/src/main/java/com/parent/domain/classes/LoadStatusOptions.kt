package com.parent.domain.classes

import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import com.parent.entities.*
import io.reactivex.Single

class LoadStatusOptions(threadExecutor: ThreadExecutor, postThreadExecutor: PostThreadExecutor) :
        BaseSingleUseCase<List<StatusReport>>(threadExecutor, postThreadExecutor) {

    override fun buildUseCaseSingle(params: Params): Single<List<StatusReport>> =
            Single.fromCallable {
                val children = params.get<List<ClassChild>>(ParamsConstants.SELECTED_CHILDREN)
                val staff = params.get<List<ClassStaff>>(ParamsConstants.SELECTED_STAFF)
                val classStatuses: List<SettingStatus> = params.get(ParamsConstants.CLASS_STATUSES)
                        ?: listOf()

                val option = if (children.isNotEmpty() && staff.isNotEmpty()) StatusReport.Type.Options.OPTION_ALL
                else if (children.isNotEmpty() && staff.isEmpty()) StatusReport.Type.Options.OPTION_CHILDREN
                else if (children.isEmpty() && staff.isNotEmpty()) StatusReport.Type.Options.OPTION_STAFF
                else -1

                val statuses = mutableListOf<StatusReport>()

                when {
                    hasCheckInStatus(children, staff) -> statuses.add(buildItem(StatusReport.Type.CHECK_OUT, true))
                    hasNoAttendanceStatus(children, staff) -> statuses.add(buildItem(StatusReport.Type.CHECK_IN, true))
                    else -> statuses.add(buildItem(StatusReport.Type.CHECK_IN, false))
                }

                StatusReport.Type.STATUS_OPTIONS.keys.forEach { key ->
                    val statusOption = StatusReport.Type.STATUS_OPTIONS[key]
                    if (key != StatusReport.Type.CHECK_IN &&
                            key != StatusReport.Type.CHECK_OUT) {
                        if (key == StatusReport.Type.SICK ||
                                key == StatusReport.Type.VACATION) {
                            statuses.add(buildItem(key, true))
                        } else {
                            if (classStatuses.any { it.key == key && it.value }) {
                                if (statusOption == StatusReport.Type.Options.OPTION_ALL ||
                                        option == statusOption) {
                                    statuses.add(buildItem(key, true))
                                }
                            }
                        }
                    }
                }

                statuses
            }

    private fun isOptionAvailable(option: Int, statusOption: Int) =
            option and statusOption > 0

    private fun hasCheckInStatus(children: List<ClassChild>, staff: List<ClassStaff>) =
            children.all { it.currentStatus.type == Status.Type.CHECK_IN } &&
                    staff.all { it.currentStatus.type == Status.Type.CHECK_IN }

    private fun hasNoAttendanceStatus(children: List<ClassChild>, staff: List<ClassStaff>) =
            children.all { it.currentStatus.type.isBlank() } &&
                    staff.all { it.currentStatus.type.isBlank() }

    private fun buildItem(key: String, isEnabled: Boolean) =
            StatusReport.Builder().key(key).isEnabled(isEnabled).build()
}