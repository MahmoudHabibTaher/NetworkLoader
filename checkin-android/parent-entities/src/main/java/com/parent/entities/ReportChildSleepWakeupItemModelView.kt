package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Raed Ezzat on 20/03/2018.
 */
class ReportChildSleepWakeupItemModelView(var childModelView: ClassChild = ClassChild(), var timeType: Int = NOW,
                                          var time: Long = 0L, var type: Int = ACTION_SLEEP) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(ClassChild::class.java.classLoader),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(childModelView, flags)
        parcel.writeInt(timeType)
        parcel.writeLong(time)
        parcel.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReportChildSleepWakeupItemModelView> {
        override fun createFromParcel(parcel: Parcel): ReportChildSleepWakeupItemModelView {
            return ReportChildSleepWakeupItemModelView(parcel)
        }

        override fun newArray(size: Int): Array<ReportChildSleepWakeupItemModelView?> {
            return arrayOfNulls(size)
        }

        const val ACTION_SLEEP = 0
        const val ACTION_WAKE_UP = 1

        const val NOW = 0
        const val MINUSE_10_MINUTES = 1
        const val MINUSE_20_MINUTES = 2
        const val MINUSE_30_MINUTES = 3
        const val MINUSE_1_HOUR = 4
        const val MINUSE_1_5_HOUR = 5
        const val MANUAL_TIME = 6
    }

}