package com.parent.entities


/**
 * Created by Raed Ezzat on 20/03/2018.
 */
class ReportChildToiletDiaperItemModel(
        var childId: String = "",
        var status: String = "",
        var type: String = ""){

    companion object {
        const val STATUS_BM="BM"
        const val STATUS_WET="Wet"
        const val STATUS_DRY="Dry"

        const val TYPE_TOILET="toilet"
        const val TYPE_DIAPER="diaper"
    }
}