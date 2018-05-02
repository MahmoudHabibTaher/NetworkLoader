package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Raed Ezzat on 03/01/2018.
 */
class AddressModelView(
        var street: String = "",
        var zipCode: String = "",
        var country: CountryView = CountryView(),
        var city: CityView = CityView()
        ):Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readParcelable(CountryView::class.java.classLoader),
                parcel.readParcelable(CityView::class.java.classLoader)) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(street)
                parcel.writeString(zipCode)
                parcel.writeParcelable(country, flags)
                parcel.writeParcelable(city, flags)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<AddressModelView> {
                override fun createFromParcel(parcel: Parcel): AddressModelView {
                        return AddressModelView(parcel)
                }

                override fun newArray(size: Int): Array<AddressModelView?> {
                        return arrayOfNulls(size)
                }
        }
}