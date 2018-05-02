package com.parent.entities

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
class ChildPickupModelView(
        var id: String = "",
        var childId: String = "",
        var personId: String = "",
        var name: String = "",
        var createdAt: String = "",
        var updatedAt: String = "",
        var deletedAt: String = "",
        var isPerson: Boolean = false,
        var pickupDateTime: String = "",
        var person: ChildContactModelView = ChildContactModelView(),
        var pickupText: String = ""
) : Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readParcelable(ChildContactModelView::class.java.classLoader),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(childId)
        parcel.writeString(personId)
        parcel.writeString(name)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(deletedAt)
        parcel.writeByte(if (isPerson) 1 else 0)
        parcel.writeString(pickupDateTime)
        parcel.writeParcelable(person, flags)
        parcel.writeString(pickupText)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChildPickupModelView> {
        override fun createFromParcel(parcel: Parcel): ChildPickupModelView {
            return ChildPickupModelView(parcel)
        }

        override fun newArray(size: Int): Array<ChildPickupModelView?> {
            return arrayOfNulls(size)
        }
    }
}