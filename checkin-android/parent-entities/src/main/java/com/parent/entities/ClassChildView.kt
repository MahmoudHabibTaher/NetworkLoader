package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mahmoud on 12/8/17.
 */
data class ClassChildView(val id: String = "",
                          val fullName: String = "",
                          val avatar: String = "") : Parcelable {
    class Builder : IBuilder<ClassChildView> {
        private var id = ""
        private var fullName = ""
        private var avatar = ""

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun fullName(fullName: String): Builder {
            this.fullName = fullName
            return this
        }

        fun avatar(avatar: String): Builder {
            this.avatar = avatar
            return this
        }

        override fun build(): ClassChildView =
                ClassChildView(id, fullName, avatar)
    }

    class TestBuilder {
        companion object {
            fun buildNormalClassChild() =
                    Builder()
                            .id("1")
                            .fullName("Mahmoud Habib")
                            .avatar("avatar")
                            .build()

            fun buildList() =
                    listOf(buildNormalClassChild())
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(fullName)
        writeString(avatar)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ClassChildView> = object : Parcelable.Creator<ClassChildView> {
            override fun createFromParcel(source: Parcel): ClassChildView = ClassChildView(source)
            override fun newArray(size: Int): Array<ClassChildView?> = arrayOfNulls(size)
        }
    }
}