package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 21/03/2018.
 */
class OpeningHourDayModel(
        var id: String? = "",
        var openingHour: String = "",
        var closingHour: String = "",
        var isWeekend: Boolean = false,
        var dayNumber: Int = 0,
        var dayName: String = ""
){

    companion object {
        const val MONDAY="monday"
        const val TUESDAY="tuesday"
        const val WEDNESDAY="wednesday"
        const val THURSDAY="thursday"
        const val FRIDAY="friday"
        const val SATURDAY="saturday"
        const val SUNDAY="sunday"
    }
}