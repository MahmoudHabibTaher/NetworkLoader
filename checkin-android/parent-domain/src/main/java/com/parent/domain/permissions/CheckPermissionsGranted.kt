package com.parent.domain.permissions

import com.parent.entities.Permission
import com.parent.domain.base.BaseSingleUseCase
import com.parent.domain.base.Params
import com.parent.domain.executor.PostThreadExecutor
import com.parent.domain.executor.ThreadExecutor
import io.reactivex.Single

/**
 * Created by mahmoud on 11/6/17.
 */
class CheckPermissionsGranted(threadExecutor: ThreadExecutor,
                              postThreadExecutor: PostThreadExecutor,
                              private val permissionsDataSource: PermissionsDataSource) :
        BaseSingleUseCase<Map<String, Boolean>>(threadExecutor, postThreadExecutor) {

    override fun buildUseCaseSingle(params: Params): Single<Map<String, Boolean>> =
            permissionsDataSource.loadUserPermissions().flatMap {
                checkGrantedPermissions(it, params.get(ParamsConstants.PARAM_PERMISSIONS))
            }

    private fun checkGrantedPermissions(userPermissions: List<Permission>, permissions: List<String>): Single<Map<String, Boolean>> =
            Single.fromCallable {
                permissions.map {
                    Pair(it, isGranted(it, userPermissions))
                }.toMap()
            }

    private fun isGranted(permission: String, permissions: List<Permission>) =
            permissions.any { it.code == permission }
}