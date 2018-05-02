package com.parent.domain.datetime

/**
 * Created by mahmoud on 12/10/17.
 */
class TestDateTimeManager : IDateTimeManager {


    companion object {
        const val DISPLAY_DATE_FORMAT = "dd MMM"
        const val DISPLAY_TIME_FORMAT = "HH:mm"
        const val WEEK_START_SUNDAY = 1
        const val UTC = "utc"
    }
    override fun is24Hours(): Boolean = true

    override fun getDateDisplayFormat(): String =
            DISPLAY_DATE_FORMAT

    override fun getTimeDisplayFormat(): String =
            DISPLAY_TIME_FORMAT

    override fun getWeekStart(): Int =
            WEEK_START_SUNDAY

    override fun getInstituteTimeZone(): String =
            UTC
}