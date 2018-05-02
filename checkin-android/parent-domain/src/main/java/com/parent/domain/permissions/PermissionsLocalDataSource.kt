package com.parent.domain.permissions

import com.parent.entities.Permission
import com.parent.entities.PermissionsGroup
import com.parent.entities.exceptions.EmptyListException
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 9/19/17.
 */
class PermissionsLocalDataSource(private val permissionsDao: PermissionsDao) : PermissionsDataSource {
    override fun loadPermissions(): Single<List<PermissionsGroup>> =
            permissionsDao.loadPermissions().flatMap {
                if (it.isEmpty()) {
                    Single.error(EmptyListException(""))
                } else {
                    Single.just(it)
                }
            }

    override fun loadUserPermissions(): Single<List<Permission>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun savePermissions(permissions: List<PermissionsGroup>): Completable =
            permissionsDao.savePermissions(permissions)
}