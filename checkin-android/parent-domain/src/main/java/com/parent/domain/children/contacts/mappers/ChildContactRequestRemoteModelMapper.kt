package com.parent.domain.children.contacts.mappers

import com.parent.entities.ChildContactModelRequest
import com.parent.entities.ChildContactModel
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildContactRequestRemoteModelMapper : ModelMapper<ChildContactModelRequest, ChildContactModel> {

    override fun mapFrom(item: ChildContactModelRequest): ChildContactModel =
            ChildContactModel()


    override fun mapTo(item: ChildContactModel): ChildContactModelRequest =
            ChildContactModelRequest(item.institutionId, item.fullName, checkEmpty(item.photo),
                    checkEmpty(item.relation.id), checkEmpty(item.role.id), checkEmpty(item.hasLogin), checkEmpty(item.email),
                    checkEmpty(item.phoneNumber), checkEmpty(item.hidePhoneNumber), checkEmpty(item.protectedAddress),
                    checkEmpty(item.address.street), checkEmpty(item.address.zipCode), checkEmpty(item.address.country.id),
                    checkEmpty(item.address.city.name), checkEmpty(item.mobileNumber), checkEmpty(""))

    fun checkEmpty(item: String): String? {
        if (item.equals("")) {
            return null
        } else {
            return item
        }
    }

}