package com.parent.entities

data class StatusReport(val key: String, val isEnabled: Boolean) {
    object Type {
        const val CHECK_IN = "check_in"
        const val CHECK_OUT = "check_out"

        const val SLEEP = "sleep"
        const val BUS = "bus"
        const val TRIP = "trip"
        const val TOILET = "diaper_change"
        const val OUCH_REPORT = "ouch_report"
        const val MOOD = "mood"
        const val MEALS = "meals"
        const val IN_A_MEETING = "meeting"
        const val HAS_CHILD_SICK = "child_sick"
        const val SICK = "sick"
        const val VACATION = "vacation"


        object Options {
            const val OPTION_CHILDREN = 0
            const val OPTION_STAFF = 1
            const val OPTION_ALL = 2

            const val CHECK_IN_OUT_OPTION = OPTION_ALL
            const val BUS_OPTION = OPTION_ALL
            const val TRIP_OPTION = OPTION_ALL
            const val SICK_OPTION = OPTION_ALL
            const val VACATION_OPTION = OPTION_ALL
            const val MEETING_OPTION = OPTION_STAFF
            const val HAS_CHILD_SICK_OPTION = OPTION_STAFF
            const val TOILET_OPTION = OPTION_CHILDREN
            const val SLEEP_OPTION = OPTION_CHILDREN
            const val MEAL_OPTION = OPTION_CHILDREN
            const val OUCH_OPTION = OPTION_CHILDREN
            const val MOOD_OPTION = OPTION_CHILDREN
        }

        val STATUS_OPTIONS = mapOf(StatusReport.Type.CHECK_IN to StatusReport.Type.Options.CHECK_IN_OUT_OPTION,
                StatusReport.Type.CHECK_OUT to StatusReport.Type.Options.CHECK_IN_OUT_OPTION,
                StatusReport.Type.TOILET to StatusReport.Type.Options.TOILET_OPTION,
                StatusReport.Type.MEALS to StatusReport.Type.Options.MEAL_OPTION,
                StatusReport.Type.SLEEP to StatusReport.Type.Options.SLEEP_OPTION,
                StatusReport.Type.BUS to StatusReport.Type.Options.BUS_OPTION,
                StatusReport.Type.TRIP to StatusReport.Type.Options.TRIP_OPTION,
//                StatusReport.Type.OUCH_REPORT to StatusReport.Type.Options.OUCH_OPTION,
                StatusReport.Type.MOOD to StatusReport.Type.Options.MOOD_OPTION,
                StatusReport.Type.SICK to StatusReport.Type.Options.SICK_OPTION,
                StatusReport.Type.VACATION to StatusReport.Type.Options.VACATION_OPTION,
                StatusReport.Type.IN_A_MEETING to StatusReport.Type.Options.MEETING_OPTION,
                StatusReport.Type.HAS_CHILD_SICK to StatusReport.Type.Options.HAS_CHILD_SICK_OPTION)
    }

    class Builder : IBuilder<StatusReport> {
        private var key = ""
        private var isEnabled = false

        fun key(key: String): Builder {
            this.key = key
            return this
        }

        fun isEnabled(isEnabled: Boolean): Builder {
            this.isEnabled = isEnabled
            return this
        }

        override fun build(): StatusReport =
                StatusReport(key, isEnabled)
    }
}