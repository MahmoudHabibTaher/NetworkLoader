package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by habib on 3/26/18.
 */
data class ReportBusRemoteItem(@SerializedName("id") val id: String,
                               @SerializedName("time") val time: String,
                               @SerializedName("on_bus") val onBus: Boolean,
                               @SerializedName("type") val type: String) {
    class Builder : IBuilder<ReportBusRemoteItem> {
        private var id = ""
        private var time = ""
        private var onBus = false
        private var type = ""

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun time(time: String): Builder {
            this.time = time
            return this
        }

        fun onBus(onBus: Boolean): Builder {
            this.onBus = onBus
            return this
        }

        fun type(type: String): Builder {
            this.type = type
            return this
        }

        override fun build(): ReportBusRemoteItem =
                ReportBusRemoteItem(id, time, onBus, type)
    }
}