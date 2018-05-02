package com.parent.domain.datetime

import com.parent.domain.auth.ISessionManager
import java.util.*

/**
 * Created by mahmoud on 12/5/17.
 */
class DateTimeManager(private val sessionManager: ISessionManager) : IDateTimeManager {


    companion object {
        private const val DEFAULT_DISPLAY_DATE_FORMAT = "dd MMM"
        private const val ALTERNATIVE_DISPLAY_DATE_FORMAT = "dd MMM yy"
        private const val SERVER_DEFAULT_DISPLAY_DATE_FORMAT = "DD MMM"
        private const val SERVER_ALTERNATIVE_DISPLAY_DATE_FORMAT = "DD MMM YY"
        private const val DEFAULT_DISPLAY_TIME_FORMAT = "hh:mm a"
        private const val TWENTY_FOUR_TIME_FORMAT = "HH:mm"

    }

    override fun getDateDisplayFormat(): String {
        var displayFormat = sessionManager.getCurrentInstitute().blockingGet().dateFormat.takeIf {
            it.isNotBlank()
        } ?: DEFAULT_DISPLAY_DATE_FORMAT
        return when (displayFormat) {
            SERVER_DEFAULT_DISPLAY_DATE_FORMAT -> DEFAULT_DISPLAY_DATE_FORMAT
            SERVER_ALTERNATIVE_DISPLAY_DATE_FORMAT -> ALTERNATIVE_DISPLAY_DATE_FORMAT
            else -> DEFAULT_DISPLAY_DATE_FORMAT
        }
    }


    override fun getTimeDisplayFormat(): String =
            sessionManager.getCurrentInstitute().blockingGet().timeFormat.takeIf {
                it.isNotBlank()
            } ?: DEFAULT_DISPLAY_TIME_FORMAT

    override fun getWeekStart(): Int =
            sessionManager.getCurrentInstitute().blockingGet().weekStart

    override fun getInstituteTimeZone(): String =
            sessionManager.getCurrentInstitute().blockingGet().timezone

    override fun is24Hours(): Boolean {
        return getTimeDisplayFormat().equals(TWENTY_FOUR_TIME_FORMAT)
    }
}