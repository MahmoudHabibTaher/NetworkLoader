package com.parent.entities

import java.util.*

/**
 * Created by mahmoud on 10/17/17.
 */
data class InstitutionModel(var id: String = "",
                            var countryId: String = "",
                            var cityId: String = "",
                            var companyId: String = "",
                            var name: String = "",
                            var avatar: String = "",
                            var website: String = "",
                            var contactPerson: String = "",
                            var email: String = "",
                            var phone: String = "",
                            var lunchTime: String = "",
                            var isActive: Boolean = false,
                            var timezone: String = "",
                            var weekStart: Int = Calendar.MONDAY,
                            var address: String = "",
                            var street: String = "",
                            var zipCode: String = "",
                            var latitude: String = "",
                            var longitude: String = "",
                            var checkInDiameter: String = "",
                            var dateFormat: String = "",
                            var timeFormat: String = "",
                            var showWeekNumbers: Boolean = false,
                            var totalChildren: Int = 0,
                            var totalCheckInChildren: Int = 0,
                            var totalClass: Int = 0,
                            var totalStaff: Int = 0,
                            var totalCheckInStaff: Int = 0) {
    class Builder : IBuilder<InstitutionModel> {
        private var id: String = ""
        private var countryId: String = ""
        private var cityId: String = ""
        private var companyId: String = ""
        private var name: String = ""
        private var avatar: String = ""
        private var website: String = ""
        private var contactPerson: String = ""
        private var email: String = ""
        private var phone: String = ""
        private var lunchTime: String = ""
        private var isActive: Boolean = false
        private var timezone: String = ""
        private var weekStart: Int = 0
        private var address: String = ""
        private var street: String = ""
        private var zipCode: String = ""
        private var latitude: String = ""
        private var longitude: String = ""
        private var checkInDiameter: String = ""
        private var dateFormat: String = ""
        private var timeFormat: String = ""
        private var showWeekNumbers: Boolean = false
        private var totalChildren: Int = 0
        private var totalClass: Int = 0
        private var totalCheckInChildren: Int = 0
        private var totalStaff: Int = 0
        private var totalCheckInStaff: Int = 0

        fun id(id: String): Builder {
            this.id = id
            return this
        }

        fun countryId(countryId: String): Builder {
            this.countryId = countryId
            return this
        }

        fun cityId(cityId: String): Builder {
            this.cityId = cityId
            return this
        }

        fun companyId(companyId: String): Builder {
            this.companyId = companyId
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

        fun website(website: String): Builder {
            this.website = website
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

        fun phone(phone: String): Builder {
            this.phone = phone
            return this
        }

        fun lunchTime(lunchTime: String): Builder {
            this.lunchTime = lunchTime
            return this
        }

        fun isActive(isActive: Boolean): Builder {
            this.isActive = isActive
            return this
        }

        fun timezone(timezone: String): Builder {
            this.timezone = timezone
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

        fun showWeekNumbers(showWeekNumbers: Boolean): Builder {
            this.showWeekNumbers = showWeekNumbers
            return this
        }
        fun  totalChildren(totalChildren: Int):Builder{
            this.totalChildren=totalChildren
            return this
        }
        fun  totalClass(totalClass: Int):Builder{
            this.totalClass=totalClass
            return this
        }
        fun  totalCheckInChildren(totalCheckInChildren: Int):Builder{
            this.totalCheckInChildren=totalCheckInChildren
            return this
        }
        fun  totalStaff(totalStaff: Int):Builder{
            this.totalStaff=totalStaff
            return this
        }
        fun  totalCheckInStaff(totalCheckInStaff: Int):Builder{
            this.totalCheckInStaff=totalCheckInStaff
            return this
        }


        override fun build(): InstitutionModel =
                InstitutionModel(id, countryId, cityId, companyId, name, avatar, website, contactPerson,
                        email, phone, lunchTime, isActive, timezone, weekStart, address, street,
                        zipCode, latitude, longitude, checkInDiameter, dateFormat, timeFormat, showWeekNumbers,totalChildren,totalCheckInChildren,totalClass,totalStaff,totalCheckInStaff)

    }

    class TestBuilder {
        companion object {

            fun buildValidInstitution() = Builder().id("1").countryId("1").cityId("1").companyId("1")
                    .name("kids inst").avatar("").website("").contactPerson("Person")
                    .email("demo@parent.eu").phone("12355667").lunchTime("").isActive(true)
                    .timezone("UTC").weekStart(0).address("").street("")
                    .zipCode("1234").latitude("").longitude("").totalChildren(23).totalCheckInChildren(12).totalClass(23).totalStaff(120).totalCheckInStaff(12).build()

            fun buildInstituteWithLocationInfo() = Builder().id("1").countryId("1").cityId("1").companyId("1")
                    .name("kids inst").avatar("").website("").contactPerson("Person")
                    .email("demo@parent.eu").phone("12355667").lunchTime("").isActive(true)
                    .timezone("UTC").weekStart(0).address("").street("")
                    .zipCode("1234").latitude("31.35").longitude("31.35")
                    .checkInDiameter("150").totalChildren(23).totalCheckInChildren(12).totalClass(23).totalStaff(120).totalCheckInStaff(12).build()

            fun buildInstituteWithNoLocationInfo() = Builder().id("1").countryId("1").cityId("1").companyId("1")
                    .name("kids inst").avatar("").website("").contactPerson("Person")
                    .email("demo@parent.eu").phone("12355667").lunchTime("").isActive(true)
                    .timezone("UTC").weekStart(0).address("").street("")
                    .zipCode("1234").latitude("").longitude("")
                    .checkInDiameter("").totalChildren(23).totalCheckInChildren(12).totalClass(23).totalStaff(120).totalCheckInStaff(12).build()
        }

    }
}