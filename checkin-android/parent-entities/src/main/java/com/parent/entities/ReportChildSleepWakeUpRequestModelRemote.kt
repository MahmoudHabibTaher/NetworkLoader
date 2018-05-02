package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 20/03/2018.
 */
class ReportChildSleepWakeUpRequestModelRemote(
        @SerializedName("sleep") var sleep: List<ReportChildSleepWakeUpItemModelRemote> = listOf(),
        @SerializedName("institution_id") var institutionId: String = "",
        @SerializedName("note") var note: String = "")