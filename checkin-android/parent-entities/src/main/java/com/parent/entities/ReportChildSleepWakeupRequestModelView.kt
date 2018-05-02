package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Raed Ezzat on 20/03/2018.
 */
class ReportChildSleepWakeupRequestModelView(
        var reportsList: List<ReportChildSleepWakeupItemModelView> = listOf(),
        var institutionId: String = "",
        var note: String = "") : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.createTypedArrayList(ReportChildSleepWakeupItemModelView),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(reportsList)
        parcel.writeString(institutionId)
        parcel.writeString(note)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReportChildSleepWakeupRequestModelView> {
        override fun createFromParcel(parcel: Parcel): ReportChildSleepWakeupRequestModelView {
            return ReportChildSleepWakeupRequestModelView(parcel)
        }

        override fun newArray(size: Int): Array<ReportChildSleepWakeupRequestModelView?> {
            return arrayOfNulls(size)
        }
    }

}