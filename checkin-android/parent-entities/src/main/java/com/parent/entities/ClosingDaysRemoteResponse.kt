package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by habib on 2/26/18.
 */
class ClosingDaysRemoteResponse(@SerializedName("is_existing_days") val isExistingDays: Boolean? = false,
                                @SerializedName("days") val days: List<ClosingDayRemote>? = listOf()) {
    class Builder : IBuilder<ClosingDaysRemoteResponse> {
        private var isExistingDays = false
        private var days = listOf<ClosingDayRemote>()

        fun isExistingDay(isExistingDays: Boolean): Builder {
            this.isExistingDays = isExistingDays
            return this
        }

        fun days(days: List<ClosingDayRemote>): Builder {
            this.days = days
            return this
        }

        override fun build(): ClosingDaysRemoteResponse =
                ClosingDaysRemoteResponse(isExistingDays, days)
    }
}