package com.parent.entities

/**
 * Created by mahmoud on 12/18/17.
 */
data class DateTimeDisplay(var date: Long,
                           var dateDisplay: String) {
    class Builder : IBuilder<DateTimeDisplay> {
        private var date = -1L
        private var dateDisplay = ""

        fun date(date: Long): Builder {
            this.date = date
            return this
        }

        fun dateDisplay(dateDisplay: String): Builder {
            this.dateDisplay = dateDisplay
            return this
        }

        override fun build(): DateTimeDisplay =
                DateTimeDisplay(date, dateDisplay)
    }
}