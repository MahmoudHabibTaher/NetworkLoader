package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmed Mahmoud on 20/03/2018.
 */
class ReportChildMealItemModelRemote(
        @SerializedName("child_id") var childId: String = "",
        @SerializedName("status") var status: String = "",
        @SerializedName("meal_id") var mealId: String = "")