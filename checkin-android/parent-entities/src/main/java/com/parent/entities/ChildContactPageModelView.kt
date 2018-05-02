package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Raed Ezzat on 04/01/2018.
 */
class ChildContactPageModelView(
        var currentPage: String? = "",
        var firstPageUrl: String? = "",
        var from: String? = "",
        var lastPage: String? = "",
        var lastPageUrl: String? = "",
        var nextPageUrl: String? = "",
        var path: String? = "",
        var perPage: String? = "",
        var prevPageUrl: String? = "",
        var to: String? = "",
        var total: String? = ""
) :Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(currentPage)
        parcel.writeString(firstPageUrl)
        parcel.writeString(from)
        parcel.writeString(lastPage)
        parcel.writeString(lastPageUrl)
        parcel.writeString(nextPageUrl)
        parcel.writeString(path)
        parcel.writeString(perPage)
        parcel.writeString(prevPageUrl)
        parcel.writeString(to)
        parcel.writeString(total)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChildContactPageModelView> {
        override fun createFromParcel(parcel: Parcel): ChildContactPageModelView {
            return ChildContactPageModelView(parcel)
        }

        override fun newArray(size: Int): Array<ChildContactPageModelView?> {
            return arrayOfNulls(size)
        }
    }

}