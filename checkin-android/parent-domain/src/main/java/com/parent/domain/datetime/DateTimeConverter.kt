package com.parent.domain.datetime

import android.annotation.SuppressLint
import com.parent.domain.common.log.logError
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatterBuilder
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by mahmoud on 12/15/17.
 */
class DateTimeConverter : IDateTimeConverter {
    companion object {
        private const val SERVER_SEND_DATE_FORMAT = "dd/MM/yyyy"
        private const val SERVER_SEND_TIME_FORMAT = "HH:mm:ss"
        private const val SERVER_RECEIVE_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss"
        private const val SERVER_RECEIVE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss"
    }

    override fun convertToServerDate(date: Long): String? = formatToString(date, SERVER_SEND_DATE_FORMAT)

    override fun convertToServerTime(time: Long): String? = formatToString(time, SERVER_SEND_TIME_FORMAT)

    override fun convertFromServerDate(date: String): Long? =
            parseDate(date, SERVER_RECEIVE_DATE_FORMAT)

    override fun convertFromSeverTime(time: String): Long? =
            parseDate(time, SERVER_RECEIVE_TIME_FORMAT)


    @SuppressLint("SimpleDateFormat")
    private fun formatToString(date: Long, format: String): String? {
        return try {
            val sdf = SimpleDateFormat(format, Locale.ENGLISH)
            sdf.format(date)
        } catch (ex: Exception) {
            logError("Error formatting date $date, $format", ex)
            null
        }
    }

    private fun parseDate(date: String, pattern: String): Long? {
        if (date.isBlank()) {
            return -1L
        }
        return try {
            DateTime.parse(date, dateTimeFormatter(pattern)).millis
        } catch (ex: Exception) {
            logError("Error parsing date $date", ex)
            -1L
        }
    }

    private fun dateTimeFormatter(pattern: String) =
            DateTimeFormatterBuilder().appendPattern(pattern).toFormatter()
}