package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Raed Ezzat on 05/01/2018.
 */
class ChildContactRelationModelView(
        var id:String = "",
        var title:String = "",
        var selected:Boolean = false
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeByte(if (selected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChildContactRelationModelView> {
        override fun createFromParcel(parcel: Parcel): ChildContactRelationModelView {
            return ChildContactRelationModelView(parcel)
        }

        override fun newArray(size: Int): Array<ChildContactRelationModelView?> {
            return arrayOfNulls(size)
        }
    }

}