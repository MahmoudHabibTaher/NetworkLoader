package com.parent.domain.status.mapper

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.base.mappers.mapToWith
import com.parent.entities.*

/**
 * Created by mahmoud on 11/28/17.
 */
class ReportChildrenSleepWakeUpItemRemoteModelMapper : ModelMapper<ReportChildSleepWakeUpItemModelRemote,
        ReportChildSleepWakeUpItemModel> {
    override fun mapFrom(item: ReportChildSleepWakeUpItemModelRemote): ReportChildSleepWakeUpItemModel =
            ReportChildSleepWakeUpItemModel(item.childId, item.time, item.sleep)

    override fun mapTo(item: ReportChildSleepWakeUpItemModel): ReportChildSleepWakeUpItemModelRemote =
            ReportChildSleepWakeUpItemModelRemote(item.childId, item.time, item.sleep)
}