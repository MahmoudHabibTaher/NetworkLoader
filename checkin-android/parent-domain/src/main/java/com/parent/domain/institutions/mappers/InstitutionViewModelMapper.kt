package com.parent.domain.institutions.mappers

import com.parent.entities.InstitutionModel
import com.parent.entities.InstitutionViewModel
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 10/17/17.
 */
class InstitutionViewModelMapper : ModelMapper<InstitutionModel, InstitutionViewModel> {
    override fun mapFrom(from: InstitutionModel): InstitutionViewModel =
            InstitutionViewModel(from.id, from.countryId, from.cityId, from.companyId,
                    from.name, from.avatar, from.website, from.contactPerson,
                    from.email, from.phone, from.lunchTime, from.isActive,
                    from.timezone, from.weekStart.toString(), from.address, from.street,
                    from.zipCode, from.latitude, from.longitude, from.checkInDiameter, from.dateFormat, from.timeFormat,
                    from.showWeekNumbers, from.totalChildren, from.totalClass, from.totalCheckInChildren,
                    from.totalStaff, from.totalCheckInStaff)

    override fun mapTo(to: InstitutionViewModel): InstitutionModel =
            InstitutionModel(to.id, to.countryId, to.cityId, to.companyId,
                    to.name, to.avatar, to.website, to.contactPerson,
                    to.email, to.phone, to.lunchTime, to.isActive,
                    to.timezone, to.weekStart.toIntOrNull() ?: 0, to.address, to.street,
                    to.zipCode, to.latitude, to.longitude, to.checkInDiameter, to.dateFormat, to.timeFormat,
                    to.showWeekNumbers, to.totalChildren, to.totalClass, to.totalCheckInChildren,
                    to.totalStaff, to.totalCheckInStaff)
}