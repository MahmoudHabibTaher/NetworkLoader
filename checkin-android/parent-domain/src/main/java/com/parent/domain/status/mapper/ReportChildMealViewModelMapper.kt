package com.parent.domain.status.mapper

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.*

/**
 * Created by mahmoud on 11/28/17.
 */
class ReportChildMealViewModelMapper : ModelMapper<ReportChildMealItemModelView,
        ReportChildMealItemModel> {
    override fun mapFrom(item: ReportChildMealItemModelView): ReportChildMealItemModel =
            ReportChildMealItemModel(item.childId, mapStatus(item.status),item.mealId)

    override fun mapTo(item: ReportChildMealItemModel): ReportChildMealItemModelView =
            ReportChildMealItemModelView(item.childId, mapStatus(item.status), false,false)


    fun mapStatus(status: String): Int =
            when (status) {
                ReportChildMealItemModel.STATUS_FULL -> {
                    ReportChildMealItemModelView.STATUS_FULL
                }
                ReportChildMealItemModel.STATUS_HALF -> {
                    ReportChildMealItemModelView.STATUS_HALF
                }
                ReportChildMealItemModel.STATUS_SOME -> {
                    ReportChildMealItemModelView.STATUS_SOME
                }
                ReportChildMealItemModel.STATUS_NON -> {
                    ReportChildMealItemModelView.STATUS_NONE
                }
                else -> {
                    ReportChildMealItemModelView.STATUS_NOTHING
                }
            }

    fun mapStatus(status: Int): String =
            when (status) {
                ReportChildMealItemModelView.STATUS_FULL -> {
                    ReportChildMealItemModel.STATUS_FULL
                }
                ReportChildMealItemModelView.STATUS_HALF -> {
                    ReportChildMealItemModel.STATUS_HALF
                }
                ReportChildMealItemModelView.STATUS_SOME -> {
                    ReportChildMealItemModel.STATUS_SOME
                }
                ReportChildMealItemModelView.STATUS_NONE -> {
                    ReportChildMealItemModel.STATUS_NON
                }
                else -> {
                    ReportChildMealItemModel.STATUS_FULL
                }
            }
}