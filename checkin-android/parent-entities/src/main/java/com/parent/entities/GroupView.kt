package com.parent.entities

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mahmoud on 10/17/17.
 */
data class GroupView(val id: String = "",
                     val name: String = "",
                     val avatar: String = "") : Parcelable {
    class Builder : IBuilder<GroupView> {
        private var id = ""
        private var name = ""
        private var avatar = ""

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun avatar(avatar: String): Builder {
            this.avatar = avatar
            return this
        }

        override fun build(): GroupView =
                GroupView(id, name, avatar)
    }

    class TestBuilder {
        companion object {
            fun buildNormalClass() =
                    Builder()
                            .id("1")
                            .name("Dolphins")
                            .avatar("avatar")
                            .build()

            fun buildList() =
                    listOf(buildNormalClass())
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
        writeString(name)
        writeString(avatar)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<GroupView> = object : Parcelable.Creator<GroupView> {
            override fun createFromParcel(source: Parcel): GroupView = GroupView(source)
            override fun newArray(size: Int): Array<GroupView?> = arrayOfNulls(size)
        }
    }
}