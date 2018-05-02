package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by habib on 3/26/18.
 */
data class ReportBusRemoteRequest(@SerializedName("bus") val items: List<ReportBusRemoteItem>,
                                  @SerializedName("institution_id") val instituteId: String) {
    class Builder : IBuilder<ReportBusRemoteRequest> {
        private var items = listOf<ReportBusRemoteItem>()
        private var instituteId = ""

        fun items(items: List<ReportBusRemoteItem>): Builder {
            this.items = items
            return this
        }

        fun instituteId(instituteId: String): Builder {
            this.instituteId = instituteId
            return this
        }

        override fun build(): ReportBusRemoteRequest =
                ReportBusRemoteRequest(items, instituteId)
    }
}