package com.parent.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 20/03/2018.
 */
class ReportChildSleepWakeUpItemModelRemote(
        @SerializedName("id") var childId: String = "",
        @SerializedName("time") var time: String = "",
        @SerializedName("sleep") var sleep: Boolean = false)