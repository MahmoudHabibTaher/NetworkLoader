package com.parent.domain.permissions

import com.parent.entities.Permission
import com.parent.entities.PermissionsGroup
import com.parent.domain.base.mappers.ModelMapper
import com.parent.domain.base.mappers.mapFromWith
import com.parent.domain.realm.base.toRealmList
import com.parent.domain.realm.entities.PermissionRealm
import com.parent.domain.realm.entities.PermissionsGroupRealm
import com.vicpin.krealmextensions.*
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by mahmoud on 10/30/17.
 */
class PermissionsRealmDao(private val permissionGroupModelMapper: ModelMapper<PermissionsGroupRealm, PermissionsGroup>,
                          private val permissionModelMapper: ModelMapper<PermissionRealm, Permission>) : PermissionsDao {
    override fun loadPermissions(): Single<List<PermissionsGroup>> =
            Single.fromCallable {
                PermissionsGroupRealm()
                        .queryAll()
                        .mapFromWith(permissionGroupModelMapper)
            }

    override fun loadPermissions(id: String): Single<Permission> =
            Single.create<PermissionRealm> { e ->
                val permission = PermissionRealm().queryFirst { it.equalTo("id", id) }

                permission?.let {
                    if (!e.isDisposed) {
                        e.onSuccess(it)
                    }
                }

                if (permission == null) {
                    if (!e.isDisposed) {
                        e.onError(NoSuchElementException("No permission found with id $id"))
                    }
                }

            }.flatMap {
                Single.just(it mapFromWith permissionModelMapper)
            }

    override fun savePermissions(permissions: List<PermissionsGroup>): Completable =
            Completable.fromAction {
                val list = permissions.map {
                    PermissionsGroupRealm(it.id, it.name, it.permissions.map {
                        PermissionRealm(it.id, it.name, it.code)
                    }.toRealmList())
                }
                list.saveAll()
            }

    override fun savePermission(permission: Permission): Completable =
            Completable.fromAction {
                PermissionRealm(permission.id, permission.name).save()
            }

    override fun deleteAll(): Completable =
            Completable.fromAction {
                PermissionsGroupRealm().deleteAll()
                PermissionRealm().deleteAll()
            }
}