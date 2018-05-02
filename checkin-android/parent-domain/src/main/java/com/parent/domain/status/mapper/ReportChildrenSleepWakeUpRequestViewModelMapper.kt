package com.parent.domain.status.mapper

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.base.mappers.mapToWith
import com.parent.entities.*

/**
 * Created by mahmoud on 11/28/17.
 */
class ReportChildrenSleepWakeUpRequestViewModelMapper(var itemModelMapper: ModelMapper<ReportChildSleepWakeupItemModelView,
        ReportChildSleepWakeUpItemModel>) : ModelMapper<ReportChildSleepWakeupRequestModelView,
        ReportChildSleepWakeUpRequestModel> {
    override fun mapFrom(item: ReportChildSleepWakeupRequestModelView): ReportChildSleepWakeUpRequestModel =
            ReportChildSleepWakeUpRequestModel(item.reportsList.mapFromWith(itemModelMapper), item.institutionId, item.note)

    override fun mapTo(item: ReportChildSleepWakeUpRequestModel): ReportChildSleepWakeupRequestModelView =
            ReportChildSleepWakeupRequestModelView(item.sleep.mapToWith(itemModelMapper), item.institutionId, item.note)
}