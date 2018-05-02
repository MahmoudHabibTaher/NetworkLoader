package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mahmoud on 11/28/17.
 */
data class CountryView(val id: String = "",
                       val name: String = "",
                       val code: String = "",
                       val cities: List<CityView> = emptyList()):Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createTypedArrayList(CityView.CREATOR)) {
    }

    override fun toString(): String = name

    class Builder : IBuilder<CountryView> {
        private var id = ""
        private var name = ""
        private var code = ""
        private var cities = emptyList<CityView>()

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun code(code: String): Builder {
            this.code = code
            return this
        }

        fun cities(cities: List<CityView>): Builder {
            this.cities = cities
            return this
        }

        override fun build(): CountryView =
                CountryView(id, name, code, cities)
    }

    class TestBuilder {
        companion object {
            fun buildNormalCountry() =
                    Builder().id("1")
                            .name("Egypt")
                            .code("EG")
                            .build()

            fun buildNormalCountryWithCities() =
                    Builder().id("1")
                            .name("Egypt")
                            .code("EG")
                            .cities(CityView.TestBuilder.buildList())
                            .build()

            fun buildList() =
                    listOf(buildNormalCountry())
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(code)
        parcel.writeTypedList(cities)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryView> {
        override fun createFromParcel(parcel: Parcel): CountryView {
            return CountryView(parcel)
        }

        override fun newArray(size: Int): Array<CountryView?> {
            return arrayOfNulls(size)
        }
    }
}