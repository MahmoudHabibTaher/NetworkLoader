package com.parent.domain.status.mapper

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.ReportChildToiletDiaperItemModel
import com.parent.entities.ReportChildToiletDiaperItemModelView

/**
 * Created by mahmoud on 11/28/17.
 */
class ReportChildToiletDiaperViewModelMapper : ModelMapper<ReportChildToiletDiaperItemModelView,
        ReportChildToiletDiaperItemModel> {
    override fun mapFrom(item: ReportChildToiletDiaperItemModelView): ReportChildToiletDiaperItemModel =
            ReportChildToiletDiaperItemModel(item.childId, mapStatus(item.status), mapType(item.type))

    override fun mapTo(item: ReportChildToiletDiaperItemModel): ReportChildToiletDiaperItemModelView =
            ReportChildToiletDiaperItemModelView(item.childId, mapStatus(item.status), mapType(item.type), false)


    fun mapStatus(status: String): Int =
            when (status) {
                ReportChildToiletDiaperItemModel.STATUS_BM -> {
                    ReportChildToiletDiaperItemModelView.STATUS_BM
                }
                ReportChildToiletDiaperItemModel.STATUS_DRY -> {
                    ReportChildToiletDiaperItemModelView.STATUS_DRY
                }
                ReportChildToiletDiaperItemModel.STATUS_WET -> {
                    ReportChildToiletDiaperItemModelView.STATUS_WET
                }
                else -> {
                    ReportChildToiletDiaperItemModelView.STATUS_BM
                }
            }

    fun mapStatus(status: Int): String =
            when (status) {
                ReportChildToiletDiaperItemModelView.STATUS_BM -> {
                    ReportChildToiletDiaperItemModel.STATUS_BM
                }
                ReportChildToiletDiaperItemModelView.STATUS_DRY -> {
                    ReportChildToiletDiaperItemModel.STATUS_DRY
                }
                ReportChildToiletDiaperItemModelView.STATUS_WET -> {
                    ReportChildToiletDiaperItemModel.STATUS_WET
                }
                else -> {
                    ReportChildToiletDiaperItemModel.STATUS_BM
                }
            }

    fun mapType(type: String): Int =
            when (type) {
                ReportChildToiletDiaperItemModel.TYPE_DIAPER -> {
                    ReportChildToiletDiaperItemModelView.TYPE_DIAPER
                }
                ReportChildToiletDiaperItemModel.TYPE_TOILET -> {
                    ReportChildToiletDiaperItemModelView.TYPE_TOILET
                }
                else -> {
                    ReportChildToiletDiaperItemModelView.TYPE_DIAPER
                }
            }

    fun mapType(type: Int): String =
            when (type) {
                ReportChildToiletDiaperItemModelView.TYPE_DIAPER -> {
                    ReportChildToiletDiaperItemModel.TYPE_DIAPER
                }
                ReportChildToiletDiaperItemModelView.TYPE_TOILET -> {
                    ReportChildToiletDiaperItemModel.TYPE_TOILET
                }
                else -> {
                    ReportChildToiletDiaperItemModel.TYPE_DIAPER
                }
            }


}