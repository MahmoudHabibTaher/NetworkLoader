package com.parent.domain.datetime

/**
 * Created by mahmoud on 12/5/17.
 */
interface IDateTimeManager {
    fun getDateDisplayFormat(): String

    fun getTimeDisplayFormat(): String

    fun getWeekStart(): Int

    fun getInstituteTimeZone(): String

    fun is24Hours(): Boolean
}