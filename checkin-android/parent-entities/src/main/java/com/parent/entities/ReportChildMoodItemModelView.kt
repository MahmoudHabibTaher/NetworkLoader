package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Ahmed Mahmoud on 08/04/2018.
 */
class ReportChildMoodItemModelView(
        var childId: String = "",
        var status: Int = 0,
        var checked:Boolean,
        var active:Boolean) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(childId)
        parcel.writeInt(status)
        parcel.writeByte(if (checked) 1 else 0)
        parcel.writeByte(if (active) 1 else 0)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReportChildMoodItemModelView> {
        override fun createFromParcel(parcel: Parcel): ReportChildMoodItemModelView {
            return ReportChildMoodItemModelView(parcel)
        }

        override fun newArray(size: Int): Array<ReportChildMoodItemModelView?> {
            return arrayOfNulls(size)
        }

        const val STATUS_NOTHING = -1
        const val STATUS_HAPPY = 0
        const val STATUS_CONTENT = 1
        const val STATUS_FUSSY = 2
        const val STATUS_SLEEPY = 3
    }

}