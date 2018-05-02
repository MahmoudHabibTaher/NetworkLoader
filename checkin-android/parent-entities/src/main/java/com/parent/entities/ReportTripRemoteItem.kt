package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by habib on 3/26/18.
 */
data class ReportTripRemoteItem(@SerializedName("id") val id: String,
                                @SerializedName("time") val time: String,
                                @SerializedName("on_trip") val onTrip: Boolean,
                                @SerializedName("type") val type: String) {
    class Builder : IBuilder<ReportTripRemoteItem> {
        private var id = ""
        private var time = ""
        private var onTrip = false
        private var type = ""

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun time(time: String): Builder {
            this.time = time
            return this
        }

        fun onTrip(onTrip: Boolean): Builder {
            this.onTrip = onTrip
            return this
        }

        fun type(type: String): Builder {
            this.type = type
            return this
        }

        override fun build(): ReportTripRemoteItem =
                ReportTripRemoteItem(id, time, onTrip, type)
    }
}