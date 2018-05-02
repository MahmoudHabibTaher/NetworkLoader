package com.parent.domain.company

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.CompanyModel
import com.parent.entities.CompanyRemote

/**
 * Created by mahmoud on 11/7/17.
 */
class CompanyRemoteModelMapper : ModelMapper<CompanyRemote, CompanyModel> {
    override fun mapFrom(item: CompanyRemote): CompanyModel =
            CompanyModel(item.id ?: "", item.name ?: "", item.avatar ?: "", item.email ?: "",
                    item.contactName ?: "", item.address ?: "", item.contactTelephone ?: "",
                    item.totalChildren ?: 0, item.totalClass ?: 0, item.totalCheckInChildren ?: 0,
                    item.totalStaff ?: 0, item.totalCheckInStaff ?: 0
            )

    override fun mapTo(item: CompanyModel): CompanyRemote =
            CompanyRemote(item.id ?: "", item.name ?: "", item.avatar ?: "", item.email,
                    item.contactName, item.address, item.contactTelephone, item.totalChildren, item.totalClass, item.totalCheckInChildren,
                    item.totalStaff, item.totalCheckInStaff)

}