package com.parent.domain.company

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.CompanyModel
import com.parent.domain.realm.entities.CompanyModelRealm

/**
 * Created by mahmoud on 11/13/17.
 */
class CompanyModelRealmModelMapper : ModelMapper<CompanyModelRealm, CompanyModel> {
    override fun mapFrom(item: CompanyModelRealm): CompanyModel =
            CompanyModel(item.id, item.name, item.avatar, item.email, item.contactName, item.address, item.contactTelephone,
                    item.totalChildren, item.totalClass, item.totalCheckInChildren, item.totalStaff, item.totalCheckInStaff)

    override fun mapTo(item: CompanyModel): CompanyModelRealm =
            CompanyModelRealm(item.id, item.name, item.avatar, item.email, item.contactName, item.address, item.contactTelephone
                    , item.totalChildren, item.totalClass, item.totalCheckInChildren, item.totalStaff, item.totalCheckInStaff)
}