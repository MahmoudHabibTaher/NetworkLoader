package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Raed Ezzat on 05/01/2018.
 */
class ChildContactRoleModelView(

        var id:String = "",
        var title:String = "",
        var description:String = "",
        var selected:Boolean = false

):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeByte(if (selected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChildContactRoleModelView> {
        override fun createFromParcel(parcel: Parcel): ChildContactRoleModelView {
            return ChildContactRoleModelView(parcel)
        }

        override fun newArray(size: Int): Array<ChildContactRoleModelView?> {
            return arrayOfNulls(size)
        }
    }
}