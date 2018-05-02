package com.parent.domain.children.contacts.mappers

import com.parent.entities.ChildContactRoleModel
import com.parent.entities.ChildContactRoleModelView
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildContactRoleViewModelMapper : ModelMapper<ChildContactRoleModelView, ChildContactRoleModel> {

    override fun mapFrom(item: ChildContactRoleModelView): ChildContactRoleModel =
            ChildContactRoleModel(item.id, item.title, item.description)

    override fun mapTo(item: ChildContactRoleModel): ChildContactRoleModelView =
            ChildContactRoleModelView(item.id, item.title, item.description)
}