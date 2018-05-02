package com.parent.domain.groups

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.Group
import com.parent.domain.realm.entities.GroupRealm

/**
 * Created by mahmoud on 11/7/17.
 */
class GroupRealmModelMapper : ModelMapper<GroupRealm, Group> {
    override fun mapFrom(from: GroupRealm): Group =
            Group.Builder()
                    .id(from.id ?: "")
                    .name(from.name ?: "")
                    .avatar(from.avatar ?: "")
                    .build()

    override fun mapTo(to: Group): GroupRealm {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}