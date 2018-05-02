package com.parent.domain.permissions

import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.common.data.source.remote.BaseRemoteDataSource
import com.parent.entities.Permission
import com.parent.entities.PermissionGroupRemote
import com.parent.entities.PermissionsGroup
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 9/19/17.
 */
class PermissionsRemoteDataSource(private val permissionsApi: PermissionsApi,
                                  private val modelMapper: ModelMapper<PermissionGroupRemote, PermissionsGroup>) : PermissionsDataSource, BaseRemoteDataSource() {
    override fun loadPermissions(): Single<List<PermissionsGroup>> =
            callSingle(permissionsApi.getPermissions().flatMap {
                Single.just(it.data?.map { modelMapper.mapFrom(it) } ?: listOf())
            })

    override fun loadUserPermissions(): Single<List<Permission>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun savePermissions(permissions: List<PermissionsGroup>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}