package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by habib on 3/25/18.
 */
data class CheckInOutRemoteRequest(@SerializedName("children") val children: List<String>,
                                   @SerializedName("employees") val staff: List<String>,
                                   @SerializedName("institution_id") val instituteId: String) {
    class Builder : IBuilder<CheckInOutRemoteRequest> {
        private var children = listOf<String>()
        private var staff = listOf<String>()
        private var instituteId = ""

        fun children(children: List<String>): Builder {
            this.children = children
            return this
        }

        fun staff(staff: List<String>): Builder {
            this.staff = staff
            return this
        }

        fun instituteId(instituteId: String): Builder {
            this.instituteId = instituteId
            return this
        }

        override fun build(): CheckInOutRemoteRequest =
                CheckInOutRemoteRequest(children, staff, instituteId)
    }
}