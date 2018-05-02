package com.parent.domain.permissions

import io.reactivex.Single

/**
 * Created by mahmoud on 11/9/17.
 */
interface IPermissionsManager {
    fun isPermissionsGranted(permissions: List<String>): Single<Map<String, Boolean>>
}