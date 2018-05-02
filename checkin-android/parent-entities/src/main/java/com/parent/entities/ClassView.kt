package com.parent.entities

import android.os.Parcel
import android.os.Parcelable
import android.view.View

/**
 * Created by mahmoud on 10/17/17.
 */
data class ClassView(val id: String = "",
                     val name: String = "",
                     val avatar: String = "",
                     val totalChildren: Int = 0,
                     val totalChildrenCheckedIn: Int = 0,
                     val status: List<Status>? = listOf(),
                     var expanded: Int = View.GONE) : Parcelable {
    class Builder : IBuilder<ClassView> {
        private var id = ""
        private var name = ""
        private var avatar = ""
        private var totalChildren: Int = 0
        private var totalChildrenCheckedIn: Int = 0
        private var status: List<Status>? = listOf()
        private var expanded: Int = View.GONE

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

        fun totalChildren(totalChildren: Int): Builder {
            this.totalChildren = totalChildren
            return this
        }

        fun totalChildrenCheckedIn(totalChildrenCheckedIn: Int): Builder {
            this.totalChildrenCheckedIn = totalChildrenCheckedIn
            return this
        }

        fun status(status: List<Status>?): Builder {
            this.status = status
            return this
        }

        fun setExpanded(expanded: Int): Builder {
            this.expanded = expanded
            return this
        }

        override fun build(): ClassView =
                ClassView(id, name, avatar, totalChildren, totalChildrenCheckedIn, status)
    }

    class TestBuilder {
        companion object {
            fun buildNormalClass() =
                    Builder()
                            .id("1")
                            .name("Dolphins")
                            .avatar("avatar")
                            .totalChildren(40)
                            .totalChildrenCheckedIn(20)
                            .status(listOf())
                            .setExpanded(View.GONE)
                            .build()

            fun buildList() =
                    listOf(buildNormalClass())
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readInt()


    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(name)
        writeString(avatar)
        writeInt(totalChildren)
        writeInt(totalChildrenCheckedIn)

    }


    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ClassView> = object : Parcelable.Creator<ClassView> {
            override fun createFromParcel(source: Parcel): ClassView = ClassView(source)
            override fun newArray(size: Int): Array<ClassView?> = arrayOfNulls(size)
        }
    }
}