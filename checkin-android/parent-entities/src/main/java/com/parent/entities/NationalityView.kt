package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mahmoud on 11/28/17.
 */
data class NationalityView(val id: String = "",
                           val name: String = ""):Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun toString(): String = name

    class Builder : IBuilder<NationalityView> {
        private var id = ""
        private var name = ""

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }


        override fun build(): NationalityView =
                NationalityView(id, name)
    }

    class TestBuilder {
        companion object {
            fun buildNormalNationality() =
                    Builder().id("1")
                            .name("Egypt")
                            .build()

            fun buildNormalNationalityWithCities() =
                    Builder().id("1")
                            .name("Egypt")
                            .build()

            fun buildList() =
                    listOf(buildNormalNationality())
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NationalityView> {
        override fun createFromParcel(parcel: Parcel): NationalityView {
            return NationalityView(parcel)
        }

        override fun newArray(size: Int): Array<NationalityView?> {
            return arrayOfNulls(size)
        }
    }
}