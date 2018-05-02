package com.parent.domain.institutions.mappers

import com.parent.entities.*
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.common.lang.toBoolean

/**
 * Created by mahmoud on 12/2/17.
 */
class InstituteOptionsRemoteModelMapper(private val countryModelMapper: ModelMapper<CountryRemote, Country>,
                                        private val cityModelMapper: ModelMapper<CityRemote, City>) :
        ModelMapper<InstituteOptionsRemote, InstituteOptions> {
    override fun mapFrom(from: InstituteOptionsRemote): InstituteOptions =
            InstituteOptions.Builder()
                    .id(from.id)
                    .companyId(from.company.id)
                    .companyName(from.company.name)
                    .name(from.name)
                    .avatar(from.avatar)
                    .contactPerson(from.contactPerson)
                    .email(from.email)
                    .website(from.website)
                    .phone(from.phone)
                    .lunchTime(from.lunchTime)
                    .weekStart(from.weekStart.toIntOrNull() ?: -1)
                    .address(from.address)
                    .street(from.street)
                    .zipCode(from.zipCode)
                    .latitude(from.latitude)
                    .longitude(from.longitude)
                    .checkInDiameter(from.checkInDiameter)
                    .dateFormat(from.dateFormat)
                    .timeFormat(from.timeFormat)
                    .showWeeksNumber(from.showWeeksNumber.toBoolean())
                    .country(from.country mapFromWith countryModelMapper)
                    .city(from.city mapFromWith cityModelMapper)
                    .timezone(Timezone.Builder().key(from.timezone.key).value(from.timezone.value).build())
                    .build()

    override fun mapTo(to: InstituteOptions): InstituteOptionsRemote {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}