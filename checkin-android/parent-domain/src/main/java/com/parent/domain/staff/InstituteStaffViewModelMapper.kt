package com.parent.domain.staff

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.InstituteStaffModel
import com.parent.entities.InstituteStaffModelView

/**
 * Created by mahmoud on 10/3/17.
 */
class InstituteStaffViewModelMapper() : ModelMapper<InstituteStaffModelView, InstituteStaffModel> {

    override fun mapFrom(item: InstituteStaffModelView): InstituteStaffModel =
            InstituteStaffModel(item.id ?: "", item.fullName ?: "", item.photo
                    ?: "",
                    item.email ?: "")

    override fun mapTo(item: InstituteStaffModel): InstituteStaffModelView =
            InstituteStaffModelView(item.id ?: "", item.fullName
                    ?: "", item.photo ?: "",
                    item.email ?: "")
}