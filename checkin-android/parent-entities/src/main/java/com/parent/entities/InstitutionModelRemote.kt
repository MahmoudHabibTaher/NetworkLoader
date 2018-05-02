package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 10/17/17.
 */
class InstitutionModelRemote(
        @SerializedName("id") var id: String? = "",
        @SerializedName("country_id") var countryId: String? = "",
        @SerializedName("city_id") var cityId: String? = "",
        @SerializedName("company_id") var companyId: String? = "",
        @SerializedName("name") var name: String? = "",
        @SerializedName("avatar") var avatar: String? = "",
        @SerializedName("website") var website: String? = "",
        @SerializedName("contact_person") var contactPerson: String? = "",
        @SerializedName("email") var email: String? = "",
        @SerializedName("phone") var phone: String? = "",
        @SerializedName("lunch_time") var lunchTime: String? = "",
        @SerializedName("is_active") var isActive: String = "",
        @SerializedName("timezone") var timezone: String? = "",
        @SerializedName("week_start") var weekStart: String? = "",
        @SerializedName("address") var address: String? = "",
        @SerializedName("street") var street: String? = "",
        @SerializedName("zip_code") var zipCode: String? = "",
        @SerializedName("latitude") var latitude: String? = "",
        @SerializedName("longitude") var longitude: String? = "",
        @SerializedName("check_in_diameter") var checkInDiameter: String? = "",
        @SerializedName("date_format") var dateFormat: String? = "",
        @SerializedName("time_format") var timeFormat: String? = "",
        @SerializedName("show_weeks_number") var showWeeksNumber: Int? = 0,
        @SerializedName("total_children") var totalChildren: Int = 0,
        @SerializedName("total_checkIn_children") var totalCheckInChildren: Int = 0,
        @SerializedName("total_class") var totalClass: Int = 0,
        @SerializedName("total_staff") var totalStaff: Int = 0,
        @SerializedName("total_checkIn_staff") var totalCheckInStaff: Int = 0) {
    class Builder : IBuilder<InstitutionModelRemote> {
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
        private var isActive: String = ""
        private var timezone: String = ""
        private var weekStart: String = ""
        private var address: String = ""
        private var street: String = ""
        private var zipCode: String = ""
        private var latitude: String = ""
        private var longitude: String = ""
        private var checkInDiameter: String = ""
        private var dateFormat: String = ""
        private var timeFormat: String = ""
        private var showWeeksNumber: Int = 0
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

        fun isActive(isActive: String): Builder {
            this.isActive = isActive
            return this
        }

        fun timezone(timezone: String): Builder {
            this.timezone = timezone
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
        override fun build(): InstitutionModelRemote =
                InstitutionModelRemote(id, countryId, cityId, companyId, name, avatar, website, contactPerson,
                        email, phone, lunchTime, isActive, timezone, weekStart, address, street, zipCode, latitude, longitude,
                        checkInDiameter, dateFormat, timeFormat, showWeeksNumber,totalChildren,totalCheckInChildren,totalClass,totalStaff,totalCheckInStaff)
    }

    class TestBuilder {
        companion object {

            fun buildValidInstitution() = Builder().id("1").countryId("1").cityId("1").companyId("1")
                    .name("kids inst").avatar("").website("").contactPerson("Person")
                    .email("demo@parent.eu").phone("12355667").lunchTime("").isActive("1")
                    .timezone("UTC").weekStart("Monday").address("").street("")
                    .zipCode("1234").latitude("31").longitude("32")
                    .checkInDiameter("150").dateFormat("dd MMM")
                    .timeFormat("hh:mm a").totalChildren(23).totalCheckInChildren(12).totalClass(23).totalStaff(120).totalCheckInStaff(12).build()
        }

    }
}