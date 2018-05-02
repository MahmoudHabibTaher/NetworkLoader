package com.parent.domain.permissions

import com.parent.domain.base.mappers.ModelMapper
import com.parent.entities.Permission
import com.parent.domain.realm.entities.PermissionRealm

/**
 * Created by mahmoud on 9/26/17.
 */
class PermissionRealmModelMapper : ModelMapper<PermissionRealm, Permission> {
    override fun mapFrom(from: PermissionRealm): Permission =
            Permission.Builder()
                    .id(from.id)
                    .name(from.name)
                    .code(from.code)
                    .build()

    override fun mapTo(to: Permission): PermissionRealm =
            PermissionRealm.Builder()
                    .id(to.id)
                    .name(to.name)
                    .code(to.code)
                    .build()
}