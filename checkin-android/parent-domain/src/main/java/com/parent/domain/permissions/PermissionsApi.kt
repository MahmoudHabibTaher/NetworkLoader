package com.parent.domain.permissions

import com.parent.entities.PermissionsResponse
import com.parent.domain.common.network.ApiConstants
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by mahmoud on 9/19/17.
 */
interface PermissionsApi {
    @GET("${ApiConstants.API_VERSION}/permissions")
    fun getPermissions(): Single<PermissionsResponse>
}