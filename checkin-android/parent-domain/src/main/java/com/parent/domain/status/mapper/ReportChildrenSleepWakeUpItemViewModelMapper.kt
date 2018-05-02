package com.parent.domain.status.mapper

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.datetime.DateFormatter
import com.parent.entities.ReportChildSleepWakeUpItemModel
import com.parent.entities.ReportChildSleepWakeUpItemModelRemote
import com.parent.entities.ReportChildSleepWakeupItemModelView

/**
 * Created by mahmoud on 11/28/17.
 */
class ReportChildrenSleepWakeUpItemViewModelMapper(var dateFormatter: DateFormatter) : ModelMapper<ReportChildSleepWakeupItemModelView,
        ReportChildSleepWakeUpItemModel> {
    override fun mapTo(to: ReportChildSleepWakeUpItemModel): ReportChildSleepWakeupItemModelView {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mapFrom(item: ReportChildSleepWakeupItemModelView): ReportChildSleepWakeUpItemModel =
            ReportChildSleepWakeUpItemModel(item.childModelView.id, dateFormatter.formatToServerTimeWithSeconds(item.time),
                    item.type == ReportChildSleepWakeupItemModelView.ACTION_SLEEP)

}