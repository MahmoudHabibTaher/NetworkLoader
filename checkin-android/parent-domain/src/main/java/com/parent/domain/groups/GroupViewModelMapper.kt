package com.parent.domain.groups

import com.parent.entities.Group
import com.parent.entities.GroupView
import com.parent.domain.base.mappers.ModelMapper

/**
 * Created by mahmoud on 10/17/17.
 */
class GroupViewModelMapper : ModelMapper<Group, GroupView> {
    override fun mapFrom(from: Group): GroupView =
            GroupView.Builder()
                    .id(from.id)
                    .name(from.name)
                    .avatar(from.avatar)
                    .build()

    override fun mapTo(to: GroupView): Group =
            Group.Builder()
                    .id(to.id)
                    .name(to.name)
                    .avatar(to.avatar)
                    .build()
}