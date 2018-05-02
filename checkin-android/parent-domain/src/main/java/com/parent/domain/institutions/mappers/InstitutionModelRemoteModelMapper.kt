package com.parent.domain.institutions.mappers

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.common.lang.toBoolean
import com.parent.entities.InstitutionModel
import com.parent.entities.InstitutionModelRemote

/**
 * Created by mahmoud on 11/7/17.
 */
class InstitutionModelRemoteModelMapper : ModelMapper<InstitutionModelRemote, InstitutionModel> {
    override fun mapFrom(from: InstitutionModelRemote): InstitutionModel =
            InstitutionModel(from.id ?: "",
                    from.countryId ?: "",
                    from.cityId ?: "",
                    from.companyId ?: "",
                    from.name ?: "",
                    from.avatar ?: "",
                    from.website ?: "",
                    from.contactPerson ?: "",
                    from.email ?: "",
                    from.phone ?: "",
                    from.lunchTime ?: "",
                    mapIsActive(from.isActive),
                    from.timezone ?: "",
                    from.weekStart?.toIntOrNull() ?: 0,
                    from.address ?: "",
                    from.street ?: "",
                    from.zipCode ?: "",
                    from.latitude ?: "",
                    from.longitude ?: "",
                    from.checkInDiameter ?: "",
                    from.dateFormat ?: "",
                    from.timeFormat ?: "",
                    from.showWeeksNumber?.toBoolean() ?: false,
                    from.totalChildren,
                    from.totalCheckInChildren,
                    from.totalClass,
                    from.totalStaff,
                    from.totalCheckInStaff)

    override fun mapTo(to: InstitutionModel): InstitutionModelRemote {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun mapIsActive(isActive: String): Boolean = isActive == "1"
}