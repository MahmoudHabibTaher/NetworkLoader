package com.parent.domain.permissions

import com.parent.entities.Permission
import com.parent.entities.PermissionsGroup
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.realm.entities.PermissionRealm
import com.parent.domain.realm.entities.PermissionsGroupRealm

/**
 * Created by mahmoud on 9/26/17.
 */
class PermissionsGroupRealmModelMapper(private val permissionsModelMapper: ModelMapper<PermissionRealm, Permission>) : ModelMapper<PermissionsGroupRealm, PermissionsGroup> {
    override fun mapFrom(from: PermissionsGroupRealm): PermissionsGroup =
            PermissionsGroup(from.id, from.name).apply {
                permissions = from.permissions.map { permissionsModelMapper.mapFrom(it) }
            }

    override fun mapTo(to: PermissionsGroup): PermissionsGroupRealm {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}