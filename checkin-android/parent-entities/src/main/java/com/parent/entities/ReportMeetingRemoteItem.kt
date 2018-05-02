package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by habib on 3/26/18.
 */
data class ReportMeetingRemoteItem(@SerializedName("id") val id: String,
                                   @SerializedName("time") val time: String,
                                   @SerializedName("on_meeting") val onMeeting: Boolean,
                                   @SerializedName("type") val type: String) {
    class Builder : IBuilder<ReportMeetingRemoteItem> {
        private var id = ""
        private var time = ""
        private var onMeeting = false
        private var type = ""

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun time(time: String): Builder {
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

        override fun build(): ReportMeetingRemoteItem =
                ReportMeetingRemoteItem(id, time, onMeeting, type)
    }
}