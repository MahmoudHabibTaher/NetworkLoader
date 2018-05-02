package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 12/4/17.
 */
data class UpdateInstituteOptionsRequest(@SerializedName("name") val name: String,
                                         @SerializedName("email") val email: String,
                                         @SerializedName("street") val street: String,
                                         @SerializedName("country_id") val countryId: String,
                                         @SerializedName("city_name") val cityName: String,
                                         @SerializedName("admin_name") val adminName: String,
                                         @SerializedName("image") val image: String,
                                         @SerializedName("website") val website: String,
                                         @SerializedName("phone") val phone: String,
                                         @SerializedName("lat") val latitude: String,
                                         @SerializedName("lng") val longitude: String,
                                         @SerializedName("timezone") val timezone: String,
                                         @SerializedName("zip_code") val zipCode: String,
                                         @SerializedName("week_start") val weekStart: Int,
                                         @SerializedName("company_id") val companyId: String,
                                         @SerializedName("check_in_diameter") val checkInDiameter: String,
                                         @SerializedName("date_format") val dateFormat: String,
                                         @SerializedName("time_format") val timeFormat: String,
                                         @SerializedName("show_weeks_number") val showWeeksNumber: Int) {
    class Builder : IBuilder<UpdateInstituteOptionsRequest> {
        private var name = ""
        private var email = ""
        private var street = ""
        private var countryId = ""
        private var cityName = ""
        private var adminName = ""
        private var image = ""
        private var website = ""
        private var phone = ""
        private var latitude = ""
        private var longitude = ""
        private var timezone = ""
        private var zipCode = ""
        private var weekStart = 0
        private var companyId = ""
        private var checkInDiameter = ""
        private var dateFormat = ""
        private var timeFormat = ""
        private var showWeeksNumber = 0

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun email(email: String): Builder {
            this.email = email
            return this
        }

        fun street(street: String): Builder {
            this.street = street
            return this
        }

        fun countryId(countryId: String): Builder {
            this.countryId = countryId
            return this
        }

        fun cityName(cityName: String): Builder {
            this.cityName = cityName
            return this
        }

        fun adminName(adminName: String): Builder {
            this.adminName = adminName
            return this
        }

        fun image(image: String): Builder {
            this.image = image
            return this
        }

        fun website(website: String): Builder {
            this.website = website
            return this
        }

        fun phone(phone: String): Builder {
            this.phone = phone
            return this
        }


        fun latitude(latitude: String): Builder {
            this.latitude = latitude
            return this
        }

        fun longitude(longitude: String): Builder {
            this.longitude = longitude
            return this
        }

        fun timezone(timezone: String): Builder {
            this.timezone = timezone
            return this
        }

        fun zipCode(zipCode: String): Builder {
            this.zipCode = zipCode
            return this
        }

        fun weekStart(weekStart: Int): Builder {
            this.weekStart = weekStart
            return this
        }

        fun companyId(companyId: String): Builder {
            this.companyId = companyId
            return this
        }

        fun checkInDiameter(checkInDiameter: String): Builder {
            this.checkInDiameter = checkInDiameter
            return this
        }

        fun dateFormat(dateFormat: String): Builder {
            this.dateFormat = dateFormat
            return this
        }

        fun timeFormat(timeFormat: String): Builder {
            this.timeFormat = timeFormat
            return this
        }

        fun showWeeksNumber(showWeeksNumber: Int): Builder {
            this.showWeeksNumber = showWeeksNumber
            return this
        }

        override fun build(): UpdateInstituteOptionsRequest =
                UpdateInstituteOptionsRequest(name, email, street, countryId, cityName, adminName, image, website, phone,
                        latitude, longitude, timezone, zipCode, weekStart, companyId, checkInDiameter,
                        dateFormat, timeFormat, showWeeksNumber)
    }
}