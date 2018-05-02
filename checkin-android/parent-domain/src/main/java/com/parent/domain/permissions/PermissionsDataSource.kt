package com.parent.domain.permissions

import com.parent.entities.Permission
import com.parent.entities.PermissionsGroup
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 9/19/17.
 */
interface PermissionsDataSource {
    fun loadPermissions(): Single<List<PermissionsGroup>>

    fun loadUserPermissions(): Single<List<Permission>>

    fun savePermissions(permissions: List<PermissionsGroup>): Completable
}