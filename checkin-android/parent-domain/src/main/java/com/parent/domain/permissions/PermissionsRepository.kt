package com.parent.domain.permissions

import com.parent.entities.Permission
import com.parent.entities.PermissionsGroup
import com.parent.domain.auth.AuthDataSource
import com.parent.entities.exceptions.EmptyListException
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 9/19/17.
 */
class PermissionsRepository(private val remoteDataSource: PermissionsDataSource,
                            private val localDataSource: PermissionsDataSource,
                            private val authDataSource: AuthDataSource) :
        PermissionsDataSource {
    override fun loadPermissions(): Single<List<PermissionsGroup>> =
            loadLocalPermissions().onErrorResumeNext {
                if (it is EmptyListException) {
                    loadRemotePermissions()
                } else {
                    Single.error(it)
                }
            }

    override fun loadUserPermissions(): Single<List<Permission>> =
            authDataSource.getCurrentUser().flatMap {
                Single.just(it.role.permissions)
            }

    override fun savePermissions(permissions: List<PermissionsGroup>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun loadLocalPermissions() =
            localDataSource.loadPermissions()

    private fun loadRemotePermissions() =
            remoteDataSource.loadPermissions()
                    .doOnSuccess { localDataSource.savePermissions(it).blockingAwait() }
}