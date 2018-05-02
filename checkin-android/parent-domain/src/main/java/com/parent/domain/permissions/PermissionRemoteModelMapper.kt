package com.parent.domain.permissions

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.Permission
import com.parent.entities.PermissionRemote

/**
 * Created by mahmoud on 10/25/17.
 */
class PermissionRemoteModelMapper : ModelMapper<PermissionRemote, Permission> {
    override fun mapFrom(from: PermissionRemote): Permission =
            Permission.Builder().id(from.id).name(from.name).code(from.code).build()

    override fun mapTo(to: Permission): PermissionRemote {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}