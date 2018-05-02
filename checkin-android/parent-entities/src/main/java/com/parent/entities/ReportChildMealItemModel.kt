package com.parent.entities


/**
 * Created by Ahmed Mahmoud on 20/03/2018.
 */
class ReportChildMealItemModel(
        var childId: String = "",
        var status: String = "",
        var mealId: Int = 0){

    companion object {
        const val STATUS_FULL="Full"
        const val STATUS_HALF="Half"
        const val STATUS_SOME="Some"
        const val STATUS_NON="None"
    }
}