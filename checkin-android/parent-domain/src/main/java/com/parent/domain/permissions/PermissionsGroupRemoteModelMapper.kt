package com.parent.domain.permissions

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.Permission
import com.parent.entities.PermissionsGroup
import com.parent.entities.PermissionGroupRemote
import com.parent.entities.PermissionRemote

/**
 * Created by mahmoud on 9/19/17.
 */
class PermissionsGroupRemoteModelMapper(private val permissionModelMapper: ModelMapper<PermissionRemote, Permission>) :
        ModelMapper<PermissionGroupRemote, PermissionsGroup> {
    override fun mapFrom(from: PermissionGroupRemote): PermissionsGroup =
            PermissionsGroup(from.id, from.name).apply {
                permissions = from.permissions.map { permissionModelMapper.mapFrom(it) }
            }

    override fun mapTo(to: PermissionsGroup): PermissionGroupRemote {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}