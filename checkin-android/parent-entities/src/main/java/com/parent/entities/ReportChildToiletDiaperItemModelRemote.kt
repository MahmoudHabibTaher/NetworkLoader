package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 20/03/2018.
 */
class ReportChildToiletDiaperItemModelRemote(
        @SerializedName("child_id") var childId: String = "",
        @SerializedName("status") var status: String = "",
        @SerializedName("type") var type: String = "")