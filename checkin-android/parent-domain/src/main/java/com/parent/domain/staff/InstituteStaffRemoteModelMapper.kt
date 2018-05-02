package com.parent.domain.staff

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.InstituteStaffModel
import com.parent.entities.InstituteStaffModelRemote

/**
 * Created by mahmoud on 10/3/17.
 */
class InstituteStaffRemoteModelMapper() : ModelMapper<InstituteStaffModelRemote, InstituteStaffModel> {

    override fun mapFrom(item: InstituteStaffModelRemote): InstituteStaffModel =
            InstituteStaffModel(item.id ?: "", item.fullName ?: "", item.photo
                    ?: "",
                    item.email ?: "")

    override fun mapTo(item: InstituteStaffModel): InstituteStaffModelRemote =
            InstituteStaffModelRemote(item.id ?: "", item.fullName
                    ?: "", item.photo ?: "",
                    item.email ?: "")
}