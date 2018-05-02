package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Raed Ezzat on 20/03/2018.
 */
class ReportChildToiletDiaperItemModelView(
        var childId: String = "",
        var status: Int = 0,
        var type: Int = 0,
        var checked:Boolean) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(childId)
        parcel.writeInt(status)
        parcel.writeInt(type)
        parcel.writeByte(if (checked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReportChildToiletDiaperItemModelView> {
        override fun createFromParcel(parcel: Parcel): ReportChildToiletDiaperItemModelView {
            return ReportChildToiletDiaperItemModelView(parcel)
        }

        override fun newArray(size: Int): Array<ReportChildToiletDiaperItemModelView?> {
            return arrayOfNulls(size)
        }
        const val STATUS_NOTHING = -1
        const val STATUS_BM = 0
        const val STATUS_WET = 1
        const val STATUS_DRY = 2

        const val TYPE_TOILET = 0
        const val TYPE_DIAPER = 1
    }

}