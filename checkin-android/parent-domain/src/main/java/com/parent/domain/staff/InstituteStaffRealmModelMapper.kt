package com.parent.domain.staff

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.realm.entities.InstituteStaffModelRealm
import com.parent.entities.InstituteStaffModel

/**
 * Created by mahmoud on 10/3/17.
 */
class InstituteStaffRealmModelMapper() : ModelMapper<InstituteStaffModelRealm, InstituteStaffModel> {

    override fun mapFrom(item: InstituteStaffModelRealm): InstituteStaffModel =
            InstituteStaffModel(item.id ?: "", item.fullName ?: "", item.photo
                    ?: "",
                    item.email ?: "")

    override fun mapTo(item: InstituteStaffModel): InstituteStaffModelRealm =
            InstituteStaffModelRealm(item.id ?: "", item.fullName
                    ?: "", item.photo ?: "",
                    item.email ?: "")
}