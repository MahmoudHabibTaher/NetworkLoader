package com.parent.domain.institutions.mappers

import com.parent.entities.*
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.base.mappers.mapToWith

/**
 * Created by mahmoud on 12/2/17.
 */
class InstituteOptionsViewModelMapper(private val countryViewModelMapper: ModelMapper<Country, CountryView>,
                                      private val cityViewModelMapper: ModelMapper<City, CityView>,
                                      private val timezoneViewModelMapper: ModelMapper<Timezone, TimezoneView>) : ModelMapper<InstituteOptions, InstituteOptionsView> {
    override fun mapFrom(from: InstituteOptions): InstituteOptionsView =
            InstituteOptionsView.Builder()
                    .id(from.id)
                    .name(from.name)
                    .avatar(from.avatar)
                    .contactPerson(from.contactPerson)
                    .email(from.email)
                    .website(from.website)
                    .phone(from.phone)
                    .lunchTime(from.lunchTime)
                    .weekStart(from.weekStart)
                    .address(from.address)
                    .street(from.street)
                    .zipCode(from.zipCode)
                    .latitude(from.latitude)
                    .longitude(from.longitude)
                    .checkInDiameter(from.checkInDiameter)
                    .dateFormat(from.dateFormat)
                    .timeFormat(from.timeFormat)
                    .showWeeksNumber(from.showWeeksNumber)
                    .country(from.country mapFromWith countryViewModelMapper)
                    .city(from.city mapFromWith cityViewModelMapper)
                    .timezone(from.timezone mapFromWith timezoneViewModelMapper)
                    .companyId(from.companyId)
                    .companyName(from.companyName)
                    .canEdit(from.canEdit)
                    .build()

    override fun mapTo(to: InstituteOptionsView): InstituteOptions =
            InstituteOptions.Builder()
                    .id(to.id)
                    .name(to.name)
                    .avatar(to.avatar)
                    .contactPerson(to.contactPerson)
                    .email(to.email)
                    .website(to.website)
                    .phone(to.phone)
                    .lunchTime(to.lunchTime)
                    .weekStart(to.weekStart)
                    .address(to.address)
                    .street(to.street)
                    .zipCode(to.zipCode)
                    .latitude(to.latitude)
                    .longitude(to.longitude)
                    .checkInDiameter(to.checkInDiameter)
                    .dateFormat(to.dateFormat)
                    .timeFormat(to.timeFormat)
                    .showWeeksNumber(to.showWeeksNumber)
                    .country(to.country mapToWith countryViewModelMapper)
                    .city(to.city mapToWith cityViewModelMapper)
                    .timezone(to.timezone mapToWith timezoneViewModelMapper)
                    .companyId(to.companyId)
                    .companyName(to.companyName)
                    .build()
}