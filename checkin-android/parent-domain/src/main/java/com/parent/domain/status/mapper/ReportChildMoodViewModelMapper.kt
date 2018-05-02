package com.parent.domain.status.mapper

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.ReportChildMoodItemModel
import com.parent.entities.ReportChildMoodItemModelView
import com.parent.entities.ReportChildToiletDiaperItemModel
import com.parent.entities.ReportChildToiletDiaperItemModelView

/**
 * Created by mahmoud on 11/28/17.
 */
class ReportChildMoodViewModelMapper : ModelMapper<ReportChildMoodItemModelView,
        ReportChildMoodItemModel> {
    override fun mapFrom(item: ReportChildMoodItemModelView): ReportChildMoodItemModel =
            ReportChildMoodItemModel(item.childId, mapStatus(item.status))

    override fun mapTo(item: ReportChildMoodItemModel): ReportChildMoodItemModelView =
            ReportChildMoodItemModelView(item.childId, mapStatus(item.status), false,false)


    fun mapStatus(status: String): Int =
            when (status) {
                ReportChildMoodItemModel.STATUS_HAPPY -> {
                    ReportChildMoodItemModelView.STATUS_HAPPY
                }
                ReportChildMoodItemModel.STATUS_CONTENT -> {
                    ReportChildMoodItemModelView.STATUS_CONTENT
                }
                ReportChildMoodItemModel.STATUS_FUSSY -> {
                    ReportChildMoodItemModelView.STATUS_FUSSY
                }
                ReportChildMoodItemModel.STATUS_SLEEPY -> {
                    ReportChildMoodItemModelView.STATUS_SLEEPY
                }
                else -> {
                    ReportChildMoodItemModelView.STATUS_NOTHING
                }
            }

    fun mapStatus(status: Int): String =
            when (status) {
                ReportChildMoodItemModelView.STATUS_HAPPY -> {
                    ReportChildMoodItemModel.STATUS_HAPPY
                }
                ReportChildMoodItemModelView.STATUS_CONTENT -> {
                    ReportChildMoodItemModel.STATUS_CONTENT
                }
                ReportChildMoodItemModelView.STATUS_FUSSY -> {
                    ReportChildMoodItemModel.STATUS_FUSSY
                }
                ReportChildMoodItemModelView.STATUS_SLEEPY -> {
                    ReportChildMoodItemModel.STATUS_SLEEPY
                }
                else -> {
                    ReportChildMoodItemModel.STATUS_HAPPY
                }
            }
}