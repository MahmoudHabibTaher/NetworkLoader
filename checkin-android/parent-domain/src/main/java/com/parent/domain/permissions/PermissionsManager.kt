package com.parent.domain.permissions

import com.parent.domain.base.Params
import com.parent.domain.base.SingleUseCase
import io.reactivex.Single

/**
 * Created by mahmoud on 11/9/17.
 */
class PermissionsManager(private val checkPermissionsGranted: SingleUseCase<Map<String, Boolean>>) : IPermissionsManager {
    override fun isPermissionsGranted(permissions: List<String>): Single<Map<String, Boolean>> =
            checkPermissionsGranted.getSingle(Params(ParamsConstants.PARAM_PERMISSIONS to permissions))
}