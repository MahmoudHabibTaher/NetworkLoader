package com.parent.domain.datetime

/**
 * Created by mahmoud on 12/15/17.
 */
interface IDateTimeConverter {
    fun convertToServerDate(date: Long): String?

    fun convertToServerTime(time: Long): String?

    fun convertFromServerDate(date: String): Long?

    fun convertFromSeverTime(time: String): Long?
}