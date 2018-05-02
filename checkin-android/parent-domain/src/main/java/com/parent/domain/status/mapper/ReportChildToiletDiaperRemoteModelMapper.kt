package com.parent.domain.status.mapper

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.ReportChildToiletDiaperItemModel
import com.parent.entities.ReportChildToiletDiaperItemModelRemote

/**
 * Created by mahmoud on 11/28/17.
 */
class ReportChildToiletDiaperRemoteModelMapper : ModelMapper<ReportChildToiletDiaperItemModelRemote,
        ReportChildToiletDiaperItemModel> {
    override fun mapFrom(item: ReportChildToiletDiaperItemModelRemote): ReportChildToiletDiaperItemModel =
            ReportChildToiletDiaperItemModel(item.childId ?: "", item.status ?: "", item.type ?: "")

    override fun mapTo(item: ReportChildToiletDiaperItemModel): ReportChildToiletDiaperItemModelRemote =
            ReportChildToiletDiaperItemModelRemote(item.childId, item.status, item.type)

}