package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Raed Ezzat on 20/03/2018.
 */
class ReportChildMealItemModelView(
        var childId: String = "",
        var status: Int = 0,
        var checked: Boolean,
        var active: Boolean,
        var mealId:Int=0) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(childId)
        parcel.writeInt(status)
        parcel.writeByte(if (checked) 1 else 0)
        parcel.writeByte(if (active) 1 else 0)
        parcel.writeInt(mealId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReportChildMealItemModelView> {
        override fun createFromParcel(parcel: Parcel): ReportChildMealItemModelView {
            return ReportChildMealItemModelView(parcel)
        }

        override fun newArray(size: Int): Array<ReportChildMealItemModelView?> {
            return arrayOfNulls(size)
        }

        const val STATUS_NOTHING = -1
        const val STATUS_FULL=0
        const val STATUS_HALF=1
        const val STATUS_SOME=2
        const val STATUS_NONE=3

    }

}