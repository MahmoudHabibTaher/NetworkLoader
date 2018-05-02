package com.parent.entities

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * Created by mahmoud on 12/6/17.
 */
data class ClassChild(val id: String = "",
                      val fullName: String = "",
                      val avatar: String = "",
                      val currentStatus: Status = Status.Builder().build(),
                      val checkInOutStatus: String = "",
                      val statuses: List<Status> = listOf(),
                      val registrationDate: Long = -1,
                      val registrationDateDisplay: String = "",
                      var isSelected: Boolean = false,
                      var showRegistrationDate: Boolean = false,
                      var isSleepToday: Boolean = false,
                      var isCheckInToday: Boolean = false) : Parcelable, Serializable, UserListItem {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Status::class.java.classLoader),
            parcel.readString(),
            parcel.createTypedArrayList(Status),
            parcel.readLong(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte())

    override fun getUserName(): String = fullName

    override fun getUserAvatar(): String = avatar

    class Builder : IBuilder<ClassChild> {
        private var id = ""
        private var fullName = ""
        private var avatar = ""
        private var currentStatus = Status.Builder().build()
        private var checkInOutStatus = ""
        private var statuses = listOf<Status>()
        private var registrationDate = -1L
        private var registrationDateDisplay = ""
        private var showRegistrationDate = false
        private var isSleepToday = false
        private var checkInToday = false

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

        fun registrationDate(date: Long): Builder {
            this.registrationDate = date
            return this
        }

        fun setSleepToday(isSleepToday: Boolean): Builder {
            this.isSleepToday = isSleepToday
            return this
        }

        fun registrationDataDisplay(dateDisplay: String): Builder {
            this.registrationDateDisplay = dateDisplay
            return this
        }

        fun showRegistrationDate(showRegistrationDate: Boolean): Builder {
            this.showRegistrationDate = showRegistrationDate
            return this
        }

        fun checkInToday(checkInToday: Boolean): Builder {
            this.checkInToday = checkInToday
            return this
        }

        fun clone(child: ClassChild): Builder =
                child.let {
                    id(it.id).fullName(it.fullName).avatar(it.avatar)
                            .currentStatus(it.currentStatus)
                            .checkInOutStatus(it.checkInOutStatus)
                            .statuses(it.statuses)
                            .registrationDate(it.registrationDate)
                            .registrationDataDisplay(it.registrationDateDisplay)
                            .showRegistrationDate(it.showRegistrationDate)
                            .setSleepToday(it.isSleepToday)
                            .checkInToday(it.isCheckInToday)
                }

        override fun build(): ClassChild =
                ClassChild(id, fullName, avatar, currentStatus, checkInOutStatus,
                        statuses, registrationDate, registrationDateDisplay,
                        showRegistrationDate = showRegistrationDate, isSleepToday = isSleepToday,
                        isCheckInToday = checkInToday)
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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(fullName)
        parcel.writeString(avatar)
        parcel.writeParcelable(currentStatus, flags)
        parcel.writeString(checkInOutStatus)
        parcel.writeTypedList(statuses)
        parcel.writeLong(registrationDate)
        parcel.writeString(registrationDateDisplay)
        parcel.writeByte(if (isSelected) 1 else 0)
        parcel.writeByte(if (showRegistrationDate) 1 else 0)
        parcel.writeByte(if (isSleepToday) 1 else 0)
        parcel.writeByte(if (isCheckInToday) 1 else 0)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClassChild> {
        override fun createFromParcel(parcel: Parcel): ClassChild {
            return ClassChild(parcel)
        }

        override fun newArray(size: Int): Array<ClassChild?> {
            return arrayOfNulls(size)
        }
    }
}