package com.parent.domain.status.mapper

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.ReportChildMoodItemModel
import com.parent.entities.ReportChildMoodItemModelRemote
import com.parent.entities.ReportChildToiletDiaperItemModel
import com.parent.entities.ReportChildToiletDiaperItemModelRemote

/**
 * Created by mahmoud on 11/28/17.
 */
class ReportChildMoodRemoteModelMapper : ModelMapper<ReportChildMoodItemModelRemote,
        ReportChildMoodItemModel> {
    override fun mapFrom(item: ReportChildMoodItemModelRemote): ReportChildMoodItemModel =
            ReportChildMoodItemModel(item.childId ?: "", item.status ?: "")

    override fun mapTo(item: ReportChildMoodItemModel): ReportChildMoodItemModelRemote =
            ReportChildMoodItemModelRemote(item.childId, item.status)

}