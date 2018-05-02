package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
class InstituteStaffModelView(
        var id: String = "",
        var fullName: String = "",
        var photo: String = "",
        var email: String = ""
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(fullName)
        parcel.writeString(photo)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InstituteStaffModelView> {
        override fun createFromParcel(parcel: Parcel): InstituteStaffModelView {
            return InstituteStaffModelView(parcel)
        }

        override fun newArray(size: Int): Array<InstituteStaffModelView?> {
            return arrayOfNulls(size)
        }
    }

}