package com.parent.entities

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class ClassStaff(val id: String = "",
                      val fullName: String = "",
                      val avatar: String = "",
                      val currentStatus: Status = Status.Builder().build(),
                      val checkInOutStatus: String = "",
                      val statuses: List<Status> = listOf(),
                      var isSelected: Boolean = false,
                      var checkedInToday: Boolean = false) : Serializable, Parcelable, UserListItem {

    override fun getUserName(): String = fullName

    override fun getUserAvatar(): String = avatar

    class Builder : IBuilder<ClassStaff> {
        private var id = ""
        private var fullName = ""
        private var avatar = ""
        private var currentStatus = Status.Builder().build()
        private var checkInOutStatus = ""
        private var statuses = listOf<Status>()
        private var checkedInToday = false

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun fullName(name: String): Builder {
            this.fullName = name
            return this
        }

        fun avatar(avatar: String): Builder {
            this.avatar = avatar
            return this
        }

        fun currentStatus(status: Status): Builder {
            this.currentStatus = status
            return this
        }

        fun checkInOutStatus(checkInOutStatus: String): Builder {
            this.checkInOutStatus = checkInOutStatus
            return this
        }

        fun statuses(statuses: List<Status>): Builder {
            this.statuses = statuses
            return this
        }

        fun checkedInToday(checkedInToday: Boolean): Builder {
            this.checkedInToday = checkedInToday
            return this
        }

        fun clone(staff: ClassStaff) =
                staff.let {
                    id(it.id).fullName(it.fullName)
                            .avatar(it.avatar)
                            .currentStatus(it.currentStatus)
                            .checkInOutStatus(it.checkInOutStatus)
                            .statuses(it.statuses)
                            .checkedInToday(it.checkedInToday)
                }

        override fun build(): ClassStaff =
                ClassStaff(id, fullName, avatar, currentStatus, checkInOutStatus, statuses,
                        checkedInToday = checkedInToday)
    }

    class TestBuilder {
        companion object {
            fun buildNormalChild() =
                    Builder()
                            .id("1")
                            .id("1")
                            .fullName("Mahmoud Habib")
                            .avatar("avatar")
                            .build()

            fun buildList() =
                    listOf(buildNormalChild())
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readParcelable<Status>(Status::class.java.classLoader),
            source.readString(),
            source.createTypedArrayList(Status.CREATOR),
            1 == source.readInt(),
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(fullName)
        writeString(avatar)
        writeParcelable(currentStatus, 0)
        writeString(checkInOutStatus)
        writeTypedList(statuses)
        writeInt((if (isSelected) 1 else 0))
        writeInt((if (checkedInToday) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ClassStaff> = object : Parcelable.Creator<ClassStaff> {
            override fun createFromParcel(source: Parcel): ClassStaff = ClassStaff(source)
            override fun newArray(size: Int): Array<ClassStaff?> = arrayOfNulls(size)
        }
    }
}