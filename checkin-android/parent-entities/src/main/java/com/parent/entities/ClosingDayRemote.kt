package com.parent.entities

import com.google.gson.annotations.SerializedName

data class ClosingDayRemote(@SerializedName("closing_date") val date: String? = "",
                            @SerializedName("reason") val reason: String? = "") {
    class Builder : IBuilder<ClosingDayRemote> {
        private var date = ""
        private var reason = ""
        fun date(date: String): Builder {
            this.date = date
            return this
        }

        fun reason(reason: String): Builder {
            this.reason = reason
            return this
        }

        override fun build(): ClosingDayRemote =
                ClosingDayRemote(date, reason)
    }

    class TestBuilder {
        companion object {
            fun buildClosingDay(date: String) =
                    Builder().date(date)
                            .reason("Weekend")
                            .build()

            fun buildList() =
                    listOf(buildClosingDay("12/12/2017 00:00:00"))
        }
    }
}
