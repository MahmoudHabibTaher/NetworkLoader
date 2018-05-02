package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mahmoud on 11/28/17.
 */
data class CityView(val id: String = "",
                    val name: String = "") : Parcelable {
    override fun toString(): String =
            name

    class Builder : IBuilder<CityView> {
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

        override fun build(): CityView =
                CityView(id, name)
    }

    class TestBuilder {
        companion object {
            fun buildNormalCity() =
                    Builder().id("1")
                            .name("Cairo")
                            .build()

            fun buildList() =
                    listOf(buildNormalCity())
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(name)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<CityView> = object : Parcelable.Creator<CityView> {
            override fun createFromParcel(source: Parcel): CityView = CityView(source)
            override fun newArray(size: Int): Array<CityView?> = arrayOfNulls(size)
        }
    }
}