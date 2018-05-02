package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 11/29/17.
 */
data class InstituteOptionsRemote(@SerializedName("id") val id: String = "",
                                  @SerializedName("name") val name: String = "",
                                  @SerializedName("avatar") val avatar: String = "",
                                  @SerializedName("contact_person") val contactPerson: String = "",
                                  @SerializedName("email") val email: String = "",
                                  @SerializedName("website") val website: String = "",
                                  @SerializedName("phone") val phone: String = "",
                                  @SerializedName("lunch_time") val lunchTime: String = "",
                                  @SerializedName("week_start") val weekStart: String = "",
                                  @SerializedName("address") val address: String = "",
                                  @SerializedName("street") val street: String = "",
                                  @SerializedName("zip_code") val zipCode: String = "",
                                  @SerializedName("latitude") val latitude: String = "",
                                  @SerializedName("longitude") val longitude: String = "",
                                  @SerializedName("check_in_diameter") val checkInDiameter: String = "",
                                  @SerializedName("date_format") val dateFormat: String = "",
                                  @SerializedName("time_format") val timeFormat: String = "",
                                  @SerializedName("show_weeks_number") val showWeeksNumber: Int = 0,
                                  @SerializedName("company") val company: CompanyRemote,
                                  @SerializedName("country") val country: CountryRemote,
                                  @SerializedName("city") val city: CityRemote,
                                  @SerializedName("timezone") val timezone: Timezone) {
    class Builder : IBuilder<InstituteOptionsRemote> {
        private var id = ""
        private var name = ""
        private var avatar = ""
        private var contactPerson = ""
        private var email = ""
        private var website = ""
        private var phone = ""
        private var lunchTime = ""
        private var weekStart = ""
        private var address = ""
        private var street = ""
        private var zipCode = ""
        private var latitude = ""
        private var longitude = ""
        private var checkInDiameter = ""
        private var dateFormat = ""
        private var timeFormat = ""
        private var showWeeksNumber = 0
        private var company = CompanyRemote()
        private var country = CountryRemote()
        private var city = CityRemote()
        private var timezone = Timezone()

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun avatar(avatar: String): Builder {
            this.avatar = avatar
            return this
        }

        fun contactPerson(contactPerson: String): Builder {
            this.contactPerson = contactPerson
            return this
        }

        fun email(email: String): Builder {
            this.email = email
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

        fun lunchTime(lunchTime: String): Builder {
            this.lunchTime = lunchTime
            return this
        }

        fun weekStart(weekStart: String): Builder {
            this.weekStart = weekStart
            return this
        }

        fun address(address: String): Builder {
            this.address = address
            return this
        }

        fun street(street: String): Builder {
            this.street = street
            return this
        }

        fun zipCode(zipCode: String): Builder {
            this.zipCode = zipCode
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

        fun companyRemote(company: CompanyRemote): Builder {
            this.company = company
            return this
        }

        fun country(country: CountryRemote): Builder {
            this.country = country
            return this
        }

        fun city(city: CityRemote): Builder {
            this.city = city
            return this
        }

        fun timezone(timezone: Timezone): Builder {
            this.timezone = timezone
            return this
        }

        override fun build(): InstituteOptionsRemote =
                InstituteOptionsRemote(id, name, avatar, contactPerson, email, website, phone, lunchTime, weekStart,
                        address, street, zipCode, latitude, longitude, checkInDiameter, dateFormat,
                        timeFormat, showWeeksNumber, company, country, city, timezone)
    }

    class TestBuilder {
        companion object {
            fun buildNormalInstituteOptions() =
                    Builder().id("1").name("Institute Name").avatar("avatar")
                            .contactPerson("Shady")
                            .email("shady@parent.eu")
                            .website("http://parent.eu")
                            .phone("123456789")
                            .lunchTime("12:00")
                            .weekStart("Sunday")
                            .address("close by")
                            .street("famous street")
                            .zipCode("12345")
                            .latitude("lat")
                            .longitude("long")
                            .checkInDiameter("50")
                            .dateFormat("date_format")
                            .timeFormat("time_format")
                            .showWeeksNumber(0)
                            .companyRemote(CompanyRemote("1", "Parent ApS"))
                            .country(CountryRemote.TestBuilder.buildNormalCountry())
                            .city(CityRemote.TestBuilder.buildNormalCity())
                            .timezone(Timezone.TestBuilder.buildNormalTimezone())
                            .build()
        }
    }
}