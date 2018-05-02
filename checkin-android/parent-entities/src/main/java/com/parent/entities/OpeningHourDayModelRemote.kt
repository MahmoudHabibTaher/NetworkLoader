package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 21/03/2018.
 */
class OpeningHourDayModelRemote(
        @SerializedName("id") var id: String? = "",
        @SerializedName("opening_hour") var openingHour: String? = "",
        @SerializedName("closing_hour") var closingHour: String? = "",
        @SerializedName("is_weekend") var isWeekend: String? = "",
        @SerializedName("day_number") var dayNumber: String? = "",
        @SerializedName("day_name") var dayName: String? = ""
)