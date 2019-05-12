package com.parent.domain.children.contacts.mappers

import com.parent.entities.ChildContactModel
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 10/3/17.
 */
class AddNewChildContactRequestRemoteModelMapper : ModelMapper<AddNewChildContactRequest, ChildContactModel> {

    override fun mapFrom(item: AddNewChildContactRequest): ChildContactModel =
            ChildContactModel()


    override fun mapTo(item: ChildContactModel): AddNewChildContactRequest =
            AddNewChildContactRequest(item.institutionId, item.fullName, item.relation.id, item.role.id
                    , item.hasLogin, item.email, item.mobileNumber)

    fun checkPhoto(photo: String): String? {
        if (photo.equals("")) {
            return null
        } else {
            return photo
        }
    }

}