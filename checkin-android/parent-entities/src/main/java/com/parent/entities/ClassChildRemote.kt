package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 12/8/17.
 */
data class ClassChildRemote(@SerializedName("id") var id: String? = "",
                            @SerializedName("full_name") var fullName: String? = "",
                            @SerializedName("avatar") var avatar: String? = "",
                            @SerializedName("current_status") var currentStatus: StatusRemote? = null,
                            @SerializedName("check_in_out") var checkInOutStatus: String? = "",
                            @SerializedName("statuses") var statuses: List<StatusRemote>? = listOf(),
                            @SerializedName("registeration_date") var registrationDate: String? = "",
                            @SerializedName("sleep_today") var sleepToday: Boolean? = false,
                            @SerializedName("check_in_today") var checkInToday: Boolean? = false) {
    class Builder : IBuilder<ClassChildRemote> {
        private var id = ""
        private var fullName = ""
        private var avatar = ""
        private var currentStatus: StatusRemote? = null
        private var checkInOutStatus = ""
        private var statuses = listOf<StatusRemote>()
        private var registrationDate = ""
        private var sleepToday = false
        private var checkInToday = false

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

        fun currentStatus(currentStatus: StatusRemote?): Builder {
            this.currentStatus = currentStatus
            return this
        }

        fun checkInOutStatus(checkInOutStatus: String): Builder {
            this.checkInOutStatus = checkInOutStatus
            return this
        }

        fun statuses(statuses: List<StatusRemote>): Builder {
            this.statuses = statuses
            return this
        }

        fun registrationDate(date: String): Builder {
            this.registrationDate = date
            return this
        }

        fun sleepToday(sleepToday: Boolean): Builder {
            this.sleepToday = sleepToday
            return this
        }

        fun checkInToday(checkInToday: Boolean): Builder {
            this.checkInToday = checkInToday
            return this
        }

        override fun build(): ClassChildRemote =
                ClassChildRemote(id, fullName, avatar, currentStatus, checkInOutStatus,
                        statuses, registrationDate, sleepToday, checkInToday)
    }

    class TestBuilder {
        companion object {
            fun buildNormalClassChild() =
                    Builder()
                            .id("1")
                            .id("1")
                            .fullName("Mahmoud Habib")
                            .avatar("avatar")
                            .build()

            fun buildList() =
                    listOf(buildNormalClassChild())
        }
    }
}