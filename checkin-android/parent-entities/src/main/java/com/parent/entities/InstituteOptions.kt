package com.parent.entities

/**
 * Created by mahmoud on 11/28/17.
 */
data class InstituteOptions(val id: String = "",
                            val name: String = "",
                            val avatar: String = "",
                            val contactPerson: String = "",
                            val email: String = "",
                            val website: String = "",
                            val phone: String = "",
                            val lunchTime: String = "",
                            val weekStart: Int = -1,
                            val address: String = "",
                            val street: String = "",
                            val zipCode: String = "",
                            val latitude: String = "",
                            val longitude: String = "",
                            val checkInDiameter: String = "",
                            val dateFormat: String = "",
                            val timeFormat: String = "",
                            val showWeeksNumber: Boolean = false,
                            val companyName: String = "",
                            val companyId: String = "",
                            val country: Country = Country(),
                            val city: City = City(),
                            val timezone: Timezone = Timezone(),
                            var canEdit: Boolean = false) {
    class Builder : IBuilder<InstituteOptions> {
        private var id = ""
        private var name = ""
        private var avatar = ""
        private var contactPerson = ""
        private var email = ""
        private var website = ""
        private var phone = ""
        private var lunchTime = ""
        private var weekStart = -1
        private var address = ""
        private var street = ""
        private var zipCode = ""
        private var latitude = ""
        private var longitude = ""
        private var checkInDiameter = ""
        private var dateFormat = ""
        private var timeFormat = ""
        private var showWeeksNumber = false
        private var companyName = ""
        private var companyId = ""
        private var country: Country = Country()
        private var city: City = City()
        private var timezone: Timezone = Timezone()
        private var canEdit = false

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

        fun weekStart(weekStart: Int): Builder {
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

        fun showWeeksNumber(showWeeksNumber: Boolean): Builder {
            this.showWeeksNumber = showWeeksNumber
            return this
        }

        fun companyName(companyName: String): Builder {
            this.companyName = companyName
            return this
        }

        fun companyId(companyId: String): Builder {
            this.companyId = companyId
            return this
        }

        fun country(country: Country): Builder {
            this.country = country
            return this
        }

        fun city(city: City): Builder {
            this.city = city
            return this
        }

        fun timezone(timezone: Timezone): Builder {
            this.timezone = timezone
            return this
        }

        fun canEdit(canEdit: Boolean): Builder {
            this.canEdit = canEdit
            return this
        }

        override fun build(): InstituteOptions =
                InstituteOptions(id, name, avatar, contactPerson, email, website, phone, lunchTime, weekStart,
                        address, street, zipCode, latitude, longitude, checkInDiameter, dateFormat,
                        timeFormat, showWeeksNumber, companyName, companyId, country, city, timezone, canEdit)
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
                            .weekStart(0)
                            .address("close by")
                            .street("famous street")
                            .zipCode("12345")
                            .latitude("lat")
                            .longitude("long")
                            .checkInDiameter("50")
                            .dateFormat("date_format")
                            .timeFormat("time_format")
                            .showWeeksNumber(true)
                            .companyName("Parent Aps")
                            .companyId("1")
                            .country(Country.TestBuilder.buildNormalCountry())
                            .city(City.TestBuilder.buildNormalCity())
                            .timezone(Timezone.TestBuilder.buildNormalTimezone())
                            .canEdit(false)
                            .build()

            fun buildNormalInstituteOptions(canEdit: Boolean) =
                    Builder().id("1").name("Institute Name").avatar("avatar")
                            .contactPerson("Shady")
                            .email("shady@parent.eu")
                            .website("http://parent.eu")
                            .phone("123456789")
                            .lunchTime("12:00")
                            .weekStart(0)
                            .address("close by")
                            .street("famous street")
                            .zipCode("12345")
                            .latitude("lat")
                            .longitude("long")
                            .checkInDiameter("50")
                            .dateFormat("date_format")
                            .timeFormat("time_format")
                            .showWeeksNumber(true)
                            .companyName("Parent Aps")
                            .companyId("1")
                            .country(Country.TestBuilder.buildNormalCountry())
                            .city(City.TestBuilder.buildNormalCity())
                            .timezone(Timezone.TestBuilder.buildNormalTimezone())
                            .canEdit(canEdit)
                            .build()
        }
    }
}