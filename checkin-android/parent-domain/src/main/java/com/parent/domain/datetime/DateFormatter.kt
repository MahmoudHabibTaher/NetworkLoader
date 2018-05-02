package com.parent.domain.datetime

import android.annotation.SuppressLint
import android.util.Log
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatterBuilder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by raede on 27/10/2017.
 */

@SuppressLint("SimpleDateFormat")
class DateFormatter(private val dateTimeManager: IDateTimeManager) : IDateFormatter {

    companion object {
        val DAY_OF_WEEK_FORMAT = "EEEE"
        val DAY_OF_WEEK_SHORT_FORMAT = "EEE"
        val DAY_FORMAT = "dd"
        val MONTH_FORMAT = "MMM"
        val FULL_MONTH_FORMAT = "MMMM"
        val YEAR_DATE_FORMAT = "yyyy"
        val SERVER_SEND_DATE_FORMAT = "dd/MM/yyyy"
        val SERVER_SEND_TIME_FORMAT = "HH:mm"
        val SERVER_SEND_TIME_WITH_SECONDS_FORMAT = "HH:mm:ss"
        val DURATION_TIME_FORMAT = "mm:ss"
        val SERVER_RECEIVE_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss"
        val SERVER_RECEIVE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss"

        val UTC_TIME_ZONE = "UTC"
    }

    private val dateDisplayFormat
        get() = dateTimeManager.getDateDisplayFormat()
    private val timeDisplayFormat
        get() = dateTimeManager.getTimeDisplayFormat()
    private var weekStart = dateTimeManager.getWeekStart()

    private fun getWeekStart(): Int {
        when (weekStart) {
            0 -> {
                return Calendar.SUNDAY
            }
            1 -> {
                return Calendar.MONDAY
            }
            2 -> {
                return Calendar.TUESDAY
            }
            3 -> {
                return Calendar.WEDNESDAY
            }
            4 -> {
                return Calendar.THURSDAY
            }
            5 -> {
                return Calendar.FRIDAY
            }
            6 -> {
                return Calendar.SATURDAY
            }
            else -> return Calendar.MONDAY
        }
    }

    fun formatServerReciveTimeToGMT(timeInInstitutionZone: String): String =
            format(timeInInstitutionZone, SERVER_RECEIVE_TIME_FORMAT, SERVER_RECEIVE_TIME_FORMAT,
                    dateTimeManager.getInstituteTimeZone(), UTC_TIME_ZONE)

    fun formatServerSendTimeToGMT(timeInInstitutionZone: String): String =
            format(timeInInstitutionZone, SERVER_SEND_TIME_FORMAT, SERVER_SEND_TIME_FORMAT,
                    dateTimeManager.getInstituteTimeZone(), UTC_TIME_ZONE)

    fun formatDurationTime(time: Long): String =
            format(time, DURATION_TIME_FORMAT)

    fun formatToServerDate(date: Long): String {
        val sdf = SimpleDateFormat(SERVER_SEND_DATE_FORMAT, Locale.ENGLISH)
        return sdf.format(date)
    }

    fun formatToServerDate(date: String): String =
            format(date, SERVER_RECEIVE_DATE_FORMAT, SERVER_SEND_DATE_FORMAT)

    fun formatToServerMainDate(date: String): String =
            format(date, SERVER_SEND_DATE_FORMAT, SERVER_RECEIVE_DATE_FORMAT)

    fun formatToServerMainDate(date: Long): String {
        val sdf = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT, Locale.ENGLISH)
        return sdf.format(date)
    }

    fun formatToServerTime(time: Long): String =
            format(time, SERVER_SEND_TIME_FORMAT)

    fun formatToServerTime(time: String): String =
            format(time, SERVER_RECEIVE_TIME_FORMAT, SERVER_SEND_TIME_FORMAT)

    fun formatToServerTimeWithSeconds(time: Long): String =
            format(time, SERVER_SEND_TIME_WITH_SECONDS_FORMAT)

    fun formatToServerTimeWithSeconds(time: String): String =
            format(time, SERVER_RECEIVE_TIME_FORMAT, SERVER_SEND_TIME_WITH_SECONDS_FORMAT)

    fun formatToServerTimeWithUTCTimeZon(time: String): String =
            format(time, SERVER_RECEIVE_TIME_FORMAT, SERVER_SEND_TIME_FORMAT,
                    dateTimeManager.getInstituteTimeZone(), UTC_TIME_ZONE)

    fun formatToServerMainTime(time: Long): String =
            format(time, SERVER_RECEIVE_TIME_FORMAT)

    fun formatToServerMainTime(time: String, format: String): String =
            format(time, format, SERVER_RECEIVE_TIME_FORMAT)

    fun formatToServerMainTimeWithUTCTimeZon(time: String, format: String): String =
            format(time, format, SERVER_RECEIVE_TIME_FORMAT,
                    dateTimeManager.getInstituteTimeZone(), UTC_TIME_ZONE)

    fun formatDisplayDate(date: String): String =
            format(date, SERVER_RECEIVE_DATE_FORMAT, dateDisplayFormat)

    fun formatDisplayDate(date: Long): String =
            format(date, dateDisplayFormat)

    fun formatDisplayTime(time: String): String =
            format(time, SERVER_RECEIVE_TIME_FORMAT, timeDisplayFormat)

    fun formatDisplayTime(time: Long): String =
            format(time, timeDisplayFormat)


    fun getCurrentDay(): String {
        try {
            var format = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            var calendar: Calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)

            return format.format(calendar.getTime())
        } catch (e: ParseException) {
            e.printStackTrace()
            return "0"
        }
    }

    fun getYearFromDate(date: String): String {
        try {
            var input_format = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            var date: Date = input_format.parse(date)
            var calendar: Calendar = Calendar.getInstance()
            calendar.setTime(date)
            return calendar.get(Calendar.YEAR).toString()
        } catch (e: ParseException) {
            e.printStackTrace()
            return "0"
        }
    }

    fun getWeekFromDate(date: String): Int {
        try {
            var input_format = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            var date: Date = input_format.parse(date)
            var calendar: Calendar = Calendar.getInstance()
            calendar.setTime(date)
            return calendar.get(Calendar.WEEK_OF_YEAR)
        } catch (e: ParseException) {
            e.printStackTrace()
            return 0
        }
    }

    fun getMonthFromDate(date: String): Int {
        try {
            var input_format = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            var date: Date = input_format.parse(date)
            var calendar: Calendar = Calendar.getInstance()
            calendar.setTime(date)
            return calendar.get(Calendar.MONTH)
        } catch (e: ParseException) {
            e.printStackTrace()
            return 0
        }
    }

    fun getHourFromDate(date: String): Int {
        try {
            var input_format = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            var date: Date = input_format.parse(date)
            var calendar: Calendar = Calendar.getInstance()
            calendar.setTime(date)
            return calendar.get(Calendar.HOUR_OF_DAY)
        } catch (e: ParseException) {
            e.printStackTrace()
            return 0
        }
    }

    fun getMinuteFromDate(date: String): Int {
        try {
            var input_format = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            var date: Date = input_format.parse(date)
            var calendar: Calendar = Calendar.getInstance()
            calendar.setTime(date)
            return calendar.get(Calendar.MINUTE)
        } catch (e: ParseException) {
            e.printStackTrace()
            return 0
        }
    }

    fun formatDisplayTimeToTimeStamp(nowTime: Long, time: String): Long {
        var timeFormatted = format(time, timeDisplayFormat, SERVER_RECEIVE_DATE_FORMAT)
        val cal = Calendar.getInstance()
        cal.timeInMillis = nowTime
        cal.set(Calendar.HOUR_OF_DAY, getHourFromDate(timeFormatted))
        cal.set(Calendar.MINUTE, getMinuteFromDate(timeFormatted))
        cal.set(Calendar.SECOND, 0)
        return cal.timeInMillis
    }


    fun getDateFromWeekAndYear(weekOfYear: Int, year: Int): Int {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear)
        cal.set(Calendar.DAY_OF_WEEK, 1)
        return cal.time.time.toInt()
    }

    fun getServerMainDateFromWeekAndYear(weekOfYear: Int, year: Int): String {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear)
        cal.set(Calendar.DAY_OF_WEEK, getWeekStart())
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        return formatToServerMainDate(cal.time.time)
    }

    fun getServerMainDateFromMonthAndYear(month: Int, year: Int): String {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.DAY_OF_WEEK, 1)
        return formatToServerMainDate(cal.time.time)
    }

    fun getServerMainDateFromHourAndMinute(hourOfDay: Int, minute: Int): String {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
        cal.set(Calendar.MINUTE, minute)
        return formatToServerMainDate(cal.time.time)
    }

    fun getDayOfWeek(date: String): String {
        try {
            val format = SimpleDateFormat(DAY_OF_WEEK_FORMAT)
            val primaryFormat = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            val date: Date = primaryFormat.parse(date)
            val calendar: Calendar = Calendar.getInstance()
            calendar.setTime(date)
            return format.format(calendar.getTime())
        } catch (e: ParseException) {
            e.printStackTrace()
            return "0"
        }
    }

    fun getDayOfWeek(date: Long): String =
            DateTime.now().withMillis(date).dayOfWeek().asShortText

    fun getDayOfWeekShort(date: String): String {
        try {
            val format = SimpleDateFormat(DAY_OF_WEEK_SHORT_FORMAT)
            val primaryFormat = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            val date: Date = primaryFormat.parse(date)
            val calendar: Calendar = Calendar.getInstance()
            calendar.setTime(date)
            return format.format(calendar.getTime())
        } catch (e: ParseException) {
            e.printStackTrace()
            return "0"
        }
    }

    fun getDayOfWeekShort(date: Long): String =
            DateTime.now().withMillis(date).dayOfWeek().asShortText

    fun getDay(date: String): String {
        try {
            var format = SimpleDateFormat(DAY_FORMAT)
            var primaryFormat = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            var date: Date = primaryFormat.parse(date)
            var calendar: Calendar = Calendar.getInstance()
            calendar.setTime(date)
            return format.format(calendar.getTime())
        } catch (e: ParseException) {
            e.printStackTrace()
            return "0"
        }
    }

    fun getDay(date: Long): String =
            DateTime.now().withMillis(date).dayOfMonth().asString

    fun getMonthNumber(date: String): Int {
        try {
            var primaryFormat = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            var date: Date = primaryFormat.parse(date)
            var calendar: Calendar = Calendar.getInstance()
            calendar.setTime(date)
            return calendar.get(Calendar.MONTH)
        } catch (e: ParseException) {
            e.printStackTrace()
            return 0
        }
    }

    fun getMonth(date: Long): String =
            DateTime.now().withMillis(date).monthOfYear().asString

    fun getMonthShort(date: Long): String =
            DateTime.now().withMillis(date).monthOfYear().asShortText

    fun getMonthName(date: String): String {
        try {
            var format = SimpleDateFormat(MONTH_FORMAT)
            var primaryFormat = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            var date: Date = primaryFormat.parse(date)
            var calendar: Calendar = Calendar.getInstance()
            calendar.setTime(date)
            return format.format(calendar.getTime())
        } catch (e: ParseException) {
            e.printStackTrace()
            return "0"
        }
    }

    fun getMonthFullName(date: String): String {
        try {
            var format = SimpleDateFormat(FULL_MONTH_FORMAT)
            var primaryFormat = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            var date: Date = primaryFormat.parse(date)
            var calendar: Calendar = Calendar.getInstance()
            calendar.setTime(date)
            return format.format(calendar.getTime())
        } catch (e: ParseException) {
            e.printStackTrace()
            return "0"
        }
    }

    fun getTimeInMillisFromDate(date: String): Long {
        try {
            var input_format = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            var date: Date = input_format.parse(date)
            var calendar: Calendar = Calendar.getInstance()
            calendar.setTime(date)
            return calendar.timeInMillis
        } catch (e: ParseException) {
            e.printStackTrace()
            return 0
        }
    }

    fun getTimeInMillisFromSenderDate(date: String): Long {
        try {
            var input_format = SimpleDateFormat(SERVER_SEND_DATE_FORMAT)
            var date: Date = input_format.parse(date)
            var calendar: Calendar = Calendar.getInstance()
            calendar.setTime(date)
            return calendar.timeInMillis
        } catch (e: ParseException) {
            e.printStackTrace()
            return 0
        }
    }

    fun is24Hours(): Boolean {
        return dateTimeManager.is24Hours()
    }

    fun getCurrentYear(): Int {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        return year
    }

    fun getCurrentWeekNumber(): Int {
        val cal = Calendar.getInstance()
        cal.firstDayOfWeek = getWeekStart()
        val week = cal.get(Calendar.WEEK_OF_YEAR)

        return week
    }

    fun getCurrentMonthNumber(): Int {
        val cal = Calendar.getInstance()
        val week = cal.get(Calendar.MONTH)
        return week
    }

    fun getWeekDay(date: String): Int {
        try {
            var input_format = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            var date: Date = input_format.parse(date)
            var calendar: Calendar = Calendar.getInstance()
            calendar.setTime(date)
            return calendar.get(Calendar.DAY_OF_WEEK)
        } catch (e: ParseException) {
            e.printStackTrace()
            return Calendar.MONDAY
        }
    }

    fun getWeekDay(week: Int, year: Int): String {
        try {
            val sdf = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            val calendar = Calendar.getInstance()
            calendar.clear()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.WEEK_OF_YEAR, week)
            calendar.set(Calendar.DAY_OF_WEEK, getWeekStart())
            return sdf.format(calendar.getTime())
        } catch (e: ParseException) {
            e.printStackTrace()
            return "0"
        }
    }

    fun getCurrentWeekStartDate(): String {
        val week = getCurrentWeekNumber()
        val year = getCurrentYear()
        Log.e("getCurrentWeekStartDate", "week: " + week + " year: " + year + " getWeekStart: " + getWeekStart())
        return getWeekDay(week, year)
    }

    fun getNextWeekStartDate(startDate: String): String {
        var inputFormat = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
        var date: Date = inputFormat.parse(startDate)
        var calendar: Calendar = Calendar.getInstance()
        calendar.setTime(date)
        calendar.add(Calendar.DAY_OF_YEAR, 7)
        val week = calendar.get(Calendar.WEEK_OF_YEAR)
        val year = calendar.get(Calendar.YEAR)
        return getWeekDay(week, year)
    }


    fun getTotalWeeksInYear(year: Int): Int {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, Calendar.DECEMBER)
        cal.set(Calendar.DAY_OF_MONTH, 31)
        val ordinalDay = cal.get(Calendar.DAY_OF_YEAR)
        val weekDay = cal.get(Calendar.DAY_OF_WEEK) - 1 // Sunday = 0
        val numberOfWeeks: Int = (ordinalDay - weekDay + 10) / 7
        return numberOfWeeks
    }

    fun addDayToDate(date: String, plus: Int): String {
        try {
            var inputFormat = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            var date: Date = inputFormat.parse(date)
            var calendar: Calendar = Calendar.getInstance()
            calendar.time = date
//            calendar.add(Calendar.DATE, plus)
//            return inputFormat.format(calendar.getTime())
            return formatToServerMainDate(date.time + (plus * 24 * 60 * 60 * 1000))
        } catch (e: ParseException) {
            e.printStackTrace()
            return "0"
        }
    }


    fun getNextWeek(date: String): String {
        try {
            var inputFormat = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
            var date: Date = inputFormat.parse(date)
            var calendar: Calendar = Calendar.getInstance()
            calendar.time = date
            var weekNum = calendar.get(Calendar.WEEK_OF_YEAR)
            Log.e("addWeekToDate", "Week: " + weekNum + "year: " + calendar.get(Calendar.YEAR) + "startDate: " + date)
            if (weekNum <= 51) {
                calendar.add(Calendar.WEEK_OF_YEAR, 1)
                calendar.set(Calendar.DAY_OF_WEEK, getWeekStart())
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
            } else {
                calendar.set(Calendar.WEEK_OF_YEAR, 1)
                calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR) + 1))
                calendar.set(Calendar.DAY_OF_WEEK, getWeekStart())
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
            }
            return inputFormat.format(calendar.getTime())
        } catch (e: ParseException) {
            e.printStackTrace()
            return "0"
        }
    }

    fun isCurrentDateWithinRange(startDateString: String,
                                 endDateString: String): Boolean {
        val sdf = SimpleDateFormat(SERVER_RECEIVE_DATE_FORMAT)
        sdf.isLenient = false
        try {

            val currentDateString = getCurrentDay()
            val currentDate = sdf.parse(currentDateString)
            val startDate = sdf.parse(startDateString)
            val endDate = sdf.parse(endDateString)

            val currentDateCalendar = Calendar.getInstance()
            currentDateCalendar.time = currentDate

            val startDateCalendar = Calendar.getInstance()

            startDateCalendar.time = startDate

            val endDateCalendar = Calendar.getInstance()
            endDateCalendar.time = endDate

            return ((currentDate.before(endDateCalendar.time) ||
                    currentDateString.equals(endDateString)) &&
                    (currentDate.after(startDateCalendar.time) ||
                            currentDateString.equals(startDateString)))

        } catch (e: ParseException) {
            e.printStackTrace()
            return false
        }

    }

    fun isTodayInRange(startDate: Long, endDate: Long): Boolean {
        val start = DateTime.now().withMillis(startDate).toLocalDate()
        val end = DateTime.now().withMillis(endDate).toLocalDate()
        val today = DateTime.now().toLocalDate()

        return today.isAfter(start) || today == start && today.isBefore(end) || today == end
    }

    @SuppressLint("SimpleDateFormat")
    private fun format(dateStr: String, fromFormat: String, toFormat: String, fromTimeZone: String,
                       toTimeZone: String): String {
        var formattedDate = ""
        try {
            val fromSdf = SimpleDateFormat(fromFormat)
            fromSdf.timeZone = TimeZone.getTimeZone(fromTimeZone)
            val toSdf = SimpleDateFormat(toFormat)
            toSdf.timeZone = TimeZone.getTimeZone(toTimeZone)

            var date: Date = fromSdf.parse(dateStr)

            var calendar: Calendar = Calendar.getInstance()
            calendar.time = date
            formattedDate = toSdf.format(calendar.time)
        } catch (e: ParseException) {
            e.printStackTrace()
            formattedDate = "0"
        } finally {
            return formattedDate
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun format(dateStr: String, fromFormat: String, toFormat: String): String {
        var formattedDate = ""
        try {
            val fromSdf = SimpleDateFormat(fromFormat)
            val toSdf = SimpleDateFormat(toFormat)

            var date: Date = fromSdf.parse(dateStr)

            var calendar: Calendar = Calendar.getInstance()
            calendar.time = date
            formattedDate = toSdf.format(calendar.time)
        } catch (e: ParseException) {
            e.printStackTrace()
            formattedDate = "0"
        } finally {
            return formattedDate
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun format(date: Long, format: String): String {
        var formattedDate = ""
        try {
            var calendar = Calendar.getInstance()
            calendar.timeInMillis = date
            formattedDate = SimpleDateFormat(format).format(calendar.time)
        } catch (e: ParseException) {
            e.printStackTrace()
            formattedDate = "0"
        } finally {
            return formattedDate
        }
    }


    private fun parseDate(date: String, pattern: String) =
            DateTime.parse(date, dateTimeFormatter(pattern))

    private fun dateTimeFormatter(pattern: String) =
            DateTimeFormatterBuilder().appendPattern(pattern).toFormatter()
}
