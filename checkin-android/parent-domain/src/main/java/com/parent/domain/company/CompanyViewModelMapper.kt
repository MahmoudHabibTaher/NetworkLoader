package com.parent.domain.company

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.CompanyModel
import com.parent.entities.CompanyModelView

/**
 * Created by mahmoud on 10/17/17.
 */
class CompanyViewModelMapper : ModelMapper<CompanyModel, CompanyModelView> {
    override fun mapFrom(item: CompanyModel): CompanyModelView =
            CompanyModelView(item.id, item.name, item.avatar, item.email, item.contactName, item.address, item.contactTelephone,
                    item.totalChildren, item.totalClass, item.totalCheckInChildren, item.totalStaff, item.totalCheckInStaff)

    override fun mapTo(item: CompanyModelView): CompanyModel =
            CompanyModel(item.id, item.name, item.avatar, item.email, item.contactName, item.address, item.contactTelephone,
                    item.totalChildren, item.totalClass, item.totalCheckInChildren, item.totalStaff, item.totalCheckInStaff)
}