package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by habib on 3/25/18.
 */
data class ReportLeaveRemoteRequest(@SerializedName("children") val children: List<String>,
                                    @SerializedName("employees") val staff: List<String>,
                                    @SerializedName("start_date") val startDate: String,
                                    @SerializedName("end_date") val endDate: String,
                                    @SerializedName("note") val note: String,
                                    @SerializedName("type") val type: String,
                                    @SerializedName("institution_id") val instituteId: String) {

    class Builder : IBuilder<ReportLeaveRemoteRequest> {
        private var children = listOf<String>()
        private var staff = listOf<String>()
        private var startDate = ""
        private var endDate = ""
        private var note = ""
        private var type = ""
        private var instituteId = ""

        fun children(children: List<String>): Builder {
            this.children = children
            return this
        }

        fun staff(staff: List<String>): Builder {
            this.staff = staff
            return this
        }

        fun startDate(startDate: String): Builder {
            this.startDate = startDate
            return this
        }

        fun endDate(endDate: String): Builder {
            this.endDate = endDate
            return this
        }

        fun note(note: String): Builder {
            this.note = note
            return this
        }

        fun type(type: String): Builder {
            this.type = type
            return this
        }

        fun instituteId(instituteId: String): Builder {
            this.instituteId = instituteId
            return this
        }

        override fun build(): ReportLeaveRemoteRequest =
                ReportLeaveRemoteRequest(children, staff, startDate, endDate, note, type, instituteId)
    }
}