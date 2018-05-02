package com.parent.domain.status.mapper

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.base.mappers.mapToWith
import com.parent.entities.*

/**
 * Created by mahmoud on 11/28/17.
 */
class ReportChildrenSleepWakeUpRequestRemoteModelMapper(var itemModelMapper: ModelMapper<ReportChildSleepWakeUpItemModelRemote,
        ReportChildSleepWakeUpItemModel>) : ModelMapper<ReportChildSleepWakeUpRequestModelRemote,
        ReportChildSleepWakeUpRequestModel> {
    override fun mapFrom(item: ReportChildSleepWakeUpRequestModelRemote): ReportChildSleepWakeUpRequestModel =
            ReportChildSleepWakeUpRequestModel(item.sleep.mapFromWith(itemModelMapper), item.institutionId, item.note)

    override fun mapTo(item: ReportChildSleepWakeUpRequestModel): ReportChildSleepWakeUpRequestModelRemote =
            ReportChildSleepWakeUpRequestModelRemote(item.sleep.mapToWith(itemModelMapper), item.institutionId, item.note)
}