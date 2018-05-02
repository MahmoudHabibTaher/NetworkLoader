package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by habib on 3/26/18.
 */
data class ReportTripRemoteRequest(@SerializedName("trip") val items: List<ReportTripRemoteItem>,
                                   @SerializedName("institution_id") val instituteId: String) {
    class Builder : IBuilder<ReportTripRemoteRequest> {
        private var items = listOf<ReportTripRemoteItem>()
        private var instituteId = ""

        fun items(items: List<ReportTripRemoteItem>): Builder {
            this.items = items
            return this
        }

        fun instituteId(instituteId: String): Builder {
            this.instituteId = instituteId
            return this
        }

        override fun build(): ReportTripRemoteRequest =
                ReportTripRemoteRequest(items, instituteId)
    }
}