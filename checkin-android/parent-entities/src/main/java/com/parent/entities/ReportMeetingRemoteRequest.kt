package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by habib on 3/26/18.
 */
data class ReportMeetingRemoteRequest(@SerializedName("meeting") val items: List<ReportMeetingRemoteItem>,
                                      @SerializedName("institution_id") val instituteId: String) {
    class Builder : IBuilder<ReportMeetingRemoteRequest> {
        private var items = listOf<ReportMeetingRemoteItem>()
        private var instituteId = ""

        fun items(items: List<ReportMeetingRemoteItem>): Builder {
            this.items = items
            return this
        }

        fun instituteId(instituteId: String): Builder {
            this.instituteId = instituteId
            return this
        }

        override fun build(): ReportMeetingRemoteRequest =
                ReportMeetingRemoteRequest(items, instituteId)
    }
}