package com.parent.domain.status.mapper

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.*

/**
 * Created by mahmoud on 11/28/17.
 */
class ReportChildMealRemoteModelMapper : ModelMapper<ReportChildMealItemModelRemote,
        ReportChildMealItemModel> {
    override fun mapFrom(item: ReportChildMealItemModelRemote): ReportChildMealItemModel =
            ReportChildMealItemModel(item.childId, item.status,item.mealId.toInt())

    override fun mapTo(item: ReportChildMealItemModel): ReportChildMealItemModelRemote =
            ReportChildMealItemModelRemote(item.childId, item.status,item.mealId.toString())
}