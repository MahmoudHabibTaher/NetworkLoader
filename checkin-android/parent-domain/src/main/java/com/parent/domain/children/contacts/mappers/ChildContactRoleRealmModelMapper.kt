package com.parent.domain.children.contacts.mappers

import com.parent.domain.realm.entities.ChildContactRoleModelRealm
import com.parent.entities.ChildContactRoleModel
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 10/3/17.
 */
class ChildContactRoleRealmModelMapper : ModelMapper<ChildContactRoleModelRealm, ChildContactRoleModel> {

    override fun mapFrom(item: ChildContactRoleModelRealm): ChildContactRoleModel =
            ChildContactRoleModel(item.id, item.title, item.description)

    override fun mapTo(item: ChildContactRoleModel): ChildContactRoleModelRealm =
            ChildContactRoleModelRealm(item.id, item.title, item.description)
}