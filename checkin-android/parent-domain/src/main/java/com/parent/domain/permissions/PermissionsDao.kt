package com.parent.domain.permissions

import com.parent.entities.Permission
import com.parent.entities.PermissionsGroup
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 10/30/17.
 */
interface PermissionsDao {
    fun loadPermissions(): Single<List<PermissionsGroup>>

    fun loadPermissions(id: String): Single<Permission>

    fun savePermissions(permissions: List<PermissionsGroup>): Completable

    fun savePermission(permission: Permission): Completable

    fun deleteAll(): Completable
}