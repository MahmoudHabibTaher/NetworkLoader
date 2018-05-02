package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 20/03/2018.
 */
class ReportChildSleepWakeUpItemModel(
        var childId: String = "",
        var time: String = "",
        var sleep: Boolean = false)