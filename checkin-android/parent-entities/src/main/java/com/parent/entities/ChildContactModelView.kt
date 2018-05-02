package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
class ChildContactModelView(
        var id: String = "",
        var instituteId: String = "",
        var fullName: String = "",
        var photo: String = "",
        var relation: ChildContactRelationModelView = ChildContactRelationModelView(),
        var childId: String = "",
        var hasLogin: Boolean = true,
        var phoneNumber: String = "",
        var role: ChildContactRoleModelView = ChildContactRoleModelView(),
        var email: String = "",
        var mobileNumber: String = "",
        var address: AddressModelView = AddressModelView(),
        var hidePhoneNumber: Boolean = true,
        var protectedAddress: Boolean = true,
        var relatedChildren: Int = 0,
        var currentPage: String = "",
        var firstPageUrl: String = "",
        var from: String = "",
        var path: String = "",
        var perPage: String = "",
        var prevPageUrl: String = "",
        var to: String = "",
        var total: String = ""
) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(ChildContactRelationModelView::class.java.classLoader),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readParcelable(ChildContactRoleModelView::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(AddressModelView::class.java.classLoader),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    fun copy(from: ChildContactModelView) {
        this.id = from.id
        this.instituteId = from.instituteId
        this.fullName = from.fullName
        this.photo = from.photo
        this.relation = from.relation
        this.childId = from.childId
        this.hasLogin = from.hasLogin
        this.phoneNumber = from.phoneNumber
        this.role = from.role
        this.email = from.email
        this.mobileNumber = from.mobileNumber
        this.address = from.address
        this.hidePhoneNumber = from.hidePhoneNumber
        this.protectedAddress = from.protectedAddress
        this.currentPage = from.currentPage
        this.firstPageUrl = from.firstPageUrl
        this.from = from.from
        this.prevPageUrl = from.prevPageUrl
        this.path = from.path
        this.perPage = from.perPage
        this.to = from.to
        this.total = from.total

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(instituteId)
        parcel.writeString(fullName)
        parcel.writeString(photo)
        parcel.writeParcelable(relation, flags)
        parcel.writeString(childId)
        parcel.writeByte(if (hasLogin) 1 else 0)
        parcel.writeString(phoneNumber)
        parcel.writeParcelable(role, flags)
        parcel.writeString(email)
        parcel.writeString(mobileNumber)
        parcel.writeParcelable(address, flags)
        parcel.writeByte(if (hidePhoneNumber) 1 else 0)
        parcel.writeByte(if (protectedAddress) 1 else 0)
        parcel.writeInt(relatedChildren)
        parcel.writeString(currentPage)
        parcel.writeString(firstPageUrl)
        parcel.writeString(from)
        parcel.writeString(path)
        parcel.writeString(perPage)
        parcel.writeString(prevPageUrl)
        parcel.writeString(to)
        parcel.writeString(total)
    }

    override fun describeContents(): Int {
        return 0
    }



    companion object CREATOR : Parcelable.Creator<ChildContactModelView> {
        override fun createFromParcel(parcel: Parcel): ChildContactModelView {
            return ChildContactModelView(parcel)
        }

        override fun newArray(size: Int): Array<ChildContactModelView?> {
            return arrayOfNulls(size)
        }
    }


}