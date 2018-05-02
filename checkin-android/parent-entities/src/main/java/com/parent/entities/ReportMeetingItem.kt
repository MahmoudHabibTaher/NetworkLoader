package com.parent.entities

/**
 * Created by habib on 3/26/18.
 */
data class ReportMeetingItem(var id: String,
                             var time: Long,
                             var onMeeting: Boolean,
                             var type: String,
                             var user: UserListItem?,
                             var isNow: Boolean?,
                             var timeDisplay: String?,
                             var canReport: Boolean = true,
                             var isEnabled: Boolean = true,
                             var serverTime: String = "") {
    object Type {
        const val CHILD = "child"
        const val STAFF = "staff"
    }

    class Builder : IBuilder<ReportMeetingItem> {
        private var id = ""
        private var time = -1L
        private var onMeeting = false
        private var type = ""
        private var user: UserListItem? = null
        private var isNow = false
        private var timeDisplay = ""
        private var canReport = true
        private var isEnabled = true
        private var serverTime = ""

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun time(time: Long): Builder {
            this.time = time
            return this
        }

        fun onMeeting(onMeeting: Boolean): Builder {
            this.onMeeting = onMeeting
            return this
        }

        fun type(type: String): Builder {
            this.type = type
            return this
        }

        fun user(user: UserListItem): Builder {
            this.user = user
            return this
        }

        fun isNow(isNow: Boolean): Builder {
            this.isNow = isNow
            return this
        }

        fun timeDisplay(timeDisplay: String): Builder {
            this.timeDisplay = timeDisplay
            return this
        }

        fun canReport(canReport: Boolean): Builder {
            this.canReport = canReport
            return this
        }

        fun isEnabled(isEnabled: Boolean): Builder {
            this.isEnabled = isEnabled
            return this
        }

        fun serverTime(time: String): Builder {
            this.serverTime = time
            return this
        }

        override fun build(): ReportMeetingItem =
                ReportMeetingItem(id, time, onMeeting, type, user, isNow, timeDisplay,
                        canReport, isEnabled, serverTime)
    }
}