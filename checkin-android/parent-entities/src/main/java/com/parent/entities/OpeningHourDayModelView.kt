package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Raed Ezzat on 21/03/2018.
 */
class OpeningHourDayModelView(
        var id: String? = "",
        var openingHour: Long = 0L,
        var closingHour: Long = 0L,
        var isWeekend: Boolean = false,
        var dayNumber: Int = 0,
        var dayName: String = ""
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readLong(),
            parcel.readLong(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeLong(openingHour)
        parcel.writeLong(closingHour)
        parcel.writeByte(if (isWeekend) 1 else 0)
        parcel.writeInt(dayNumber)
        parcel.writeString(dayName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OpeningHourDayModelView> {
        override fun createFromParcel(parcel: Parcel): OpeningHourDayModelView {
            return OpeningHourDayModelView(parcel)
        }

        override fun newArray(size: Int): Array<OpeningHourDayModelView?> {
            return arrayOfNulls(size)
        }
    }
}