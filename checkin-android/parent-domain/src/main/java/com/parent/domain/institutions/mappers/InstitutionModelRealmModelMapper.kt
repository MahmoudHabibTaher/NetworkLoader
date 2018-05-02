package com.parent.domain.institutions.mappers

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.realm.entities.InstitutionModelRealm
import com.parent.entities.InstitutionModel

/**
 * Created by mahmoud on 11/13/17.
 */
class InstitutionModelRealmModelMapper : ModelMapper<InstitutionModelRealm, InstitutionModel> {
    override fun mapFrom(from: InstitutionModelRealm): InstitutionModel =
            InstitutionModel(from.id, from.countryId, from.cityId, from.companyId,
                    from.name, from.avatar, from.website, from.contactPerson,
                    from.email, from.phone, from.lunchTime, from.isActive,
                    from.timezone, from.weekStart.toIntOrNull() ?: 0, from.address, from.street,
                    from.zipCode, from.latitude, from.longitude, from.checkInDiameter,
                    from.dateFormat, from.timeFormat, from.showWeeksNumber,from.totalChildren,from.totalCheckInChildren,from.totalClass,from.totalStaff,from.totalCheckInStaff)

    override fun mapTo(to: InstitutionModel): InstitutionModelRealm =
            InstitutionModelRealm(to.id, "", to.countryId, to.cityId, to.companyId,
                    to.name, to.avatar, to.website, to.contactPerson,
                    to.email, to.phone, to.lunchTime, to.isActive,
                    to.timezone, to.weekStart.toString(), to.address, to.street,
                    to.zipCode, to.latitude, to.longitude, to.checkInDiameter, to.dateFormat,
                    to.timeFormat, to.showWeekNumbers,to.totalChildren,to.totalCheckInChildren,to.totalClass,to.totalStaff,to.totalCheckInStaff)
}
