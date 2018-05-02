package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mahmoud on 12/4/17.
 */
data class CompanyModelView(val id: String = "",
                            val name: String = "",
                            val avatar: String = "",
                            val email: String = "",
                            val contactName: String = "",
                            val address: String = "",
                            val contactTelephone: String = "",
                            var totalChildren: Int = 0,
                            var totalClass: Int = 0,
                            var totalCheckInChildren: Int = 0,
                            var totalStaff: Int = 0,
                            var totalCheckInStaff: Int = 0):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(avatar)
        parcel.writeString(email)
        parcel.writeString(contactName)
        parcel.writeString(address)
        parcel.writeString(contactTelephone)
        parcel.writeInt(totalChildren)
        parcel.writeInt(totalClass)
        parcel.writeInt(totalCheckInChildren)
        parcel.writeInt(totalStaff)
        parcel.writeInt(totalCheckInStaff)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CompanyModelView> {
        override fun createFromParcel(parcel: Parcel): CompanyModelView {
            return CompanyModelView(parcel)
        }

        override fun newArray(size: Int): Array<CompanyModelView?> {
            return arrayOfNulls(size)
        }
    }

}