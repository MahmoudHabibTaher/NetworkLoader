package com.parent.entities

import java.util.*

/**
 * Created by mahmoud on 12/12/17.
 */
data class ClosingDay(val date: Long = -1,
                      val reason: String = "") {
    class Builder : IBuilder<ClosingDay> {
        private var date = -1L
        private var reason = ""

        fun date(date: Long): Builder {
            this.date = date
            return this
        }

        fun reason(reason: String): Builder {
            this.reason = reason
            return this
        }

        override fun build(): ClosingDay =
                ClosingDay(date, reason)
    }

    class TestBuilder {
        companion object {
            fun buildClosingDay() =
                    buildClosingDay(Calendar.getInstance().timeInMillis)

            fun buildClosingDay(date: Long) =
                    Builder().date(date)
                            .reason("Weekend")
                            .build()

            fun buildList() =
                    listOf(buildClosingDay(Calendar.getInstance().timeInMillis))
        }
    }
}