package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmed Mahmoud on 09/04/2018.
 */
class ReportChildMoodItemModelRemote(
        @SerializedName("child_id") var childId: String = "",
        @SerializedName("status") var status: String = "")