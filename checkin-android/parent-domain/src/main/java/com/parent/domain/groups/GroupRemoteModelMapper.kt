package com.parent.domain.groups

import com.parent.entities.Group
import com.parent.entities.GroupRemote
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 11/7/17.
 */
class GroupRemoteModelMapper : ModelMapper<GroupRemote, Group> {
    override fun mapFrom(from: GroupRemote): Group =
            Group.Builder()
                    .id(from.id ?: "")
                    .name(from.name ?: "")
                    .avatar(from.avatar ?: "")
                    .build()

    override fun mapTo(to: Group): GroupRemote =
            GroupRemote.Builder()
                    .id(to.id ?: "")
                    .name(to.name ?: "")
                    .avatar(to.avatar ?: "")
                    .build()
}