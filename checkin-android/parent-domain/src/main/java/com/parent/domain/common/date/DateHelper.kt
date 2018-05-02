package com.parent.domain.common.date

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by mahmoud on 10/28/17.
 */
object DateHelper {

}

fun now() = Calendar.getInstance().timeInMillis

fun Int.getMilliSecondsFromMinutes(): Long =
        (this * 60 * 1000).toLong()

fun Double.getMilliSecondsFromHours(): Long =
        (this * 60 * 60 * 1000).toLong()

fun String.toDate(format: String): Date {
    try {
        return SimpleDateFormat(format, Locale.ENGLISH).parse(this)
    } catch (e: ParseException) {
        return Date(now())
    }
}

fun Long.formatDate(format: String, locale: Locale = Locale.getDefault()): String {
    val sdf = SimpleDateFormat(format, locale)
    return sdf.format(this)
}

fun Long.toCalendar() = Calendar.getInstance().apply { timeInMillis = this@toCalendar }

fun Long.isBeforeNow(): Boolean {
    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND,0)
    }.timeInMillis

    val date = Calendar.getInstance().apply {
        timeInMillis = this@isBeforeNow
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND,0)

    }.timeInMillis

    return date < today
}

fun Long.isAfterDate(date: Long) =
        this > date

fun Long.isEqualDate(date: Long) =
        this == date

fun Long.isBeforeDate(date: Long) =
        this < date

fun Long.getHour(): Int {
    try {
        var calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = this
        return calendar.get(Calendar.HOUR_OF_DAY)
    } catch (e: ParseException) {
        e.printStackTrace()
        return 0
    }
}

fun Long.getMinute(): Int {
    try {
        var calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = this
        return calendar.get(Calendar.MINUTE)
    } catch (e: ParseException) {
        e.printStackTrace()
        return 0
    }
}




