package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 20/03/2018.
 */
class ReportChildSleepWakeUpRequestModel(
        var sleep: List<ReportChildSleepWakeUpItemModel> = listOf(),
        var institutionId: String = "",
        var note: String = "")