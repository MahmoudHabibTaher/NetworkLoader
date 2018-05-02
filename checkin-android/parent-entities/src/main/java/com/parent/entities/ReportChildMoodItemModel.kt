package com.parent.entities


/**
 * Created by Ahmed Mahmoud on 09/04/2018.
 */
class ReportChildMoodItemModel(
        var childId: String = "",
        var status: String = ""){

    companion object {
        const val STATUS_HAPPY = "Happy"
        const val STATUS_CONTENT = "Content"
        const val STATUS_FUSSY = "Fussy"
        const val STATUS_SLEEPY = "Sleepy"
    }
}