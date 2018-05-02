package com.parent.domain.children.contacts.mappers

import com.parent.entities.ChildContactRoleModel
import com.parent.entities.ChildContactRoleModelRemote
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildContactRoleRemoteModelMapper : ModelMapper<ChildContactRoleModelRemote, ChildContactRoleModel> {

    override fun mapFrom(item: ChildContactRoleModelRemote): ChildContactRoleModel =
            ChildContactRoleModel(item.id ?: "", item.name
                    ?: "", item.description ?: "")

    override fun mapTo(item: ChildContactRoleModel): ChildContactRoleModelRemote =
            ChildContactRoleModelRemote(item.id ?: "", item.title
                    ?: "", item.description ?: "")
}