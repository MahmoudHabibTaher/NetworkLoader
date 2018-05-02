package com.parent.domain.institutions

import com.parent.entities.BaseResponse
import com.parent.entities.InstituteOptionsRemote
import com.parent.entities.UpdateInstituteOptionsRequest
import com.parent.domain.common.network.ApiConstants
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

/**
 * Created by mahmoud on 11/29/17.
 */
interface InstituteOptionsApi {
    @GET("${ApiConstants.API_VERSION}/institutions/{id}/options")
    fun getInstituteOptions(@Path("id") instituteId: String): Single<BaseResponse<InstituteOptionsRemote>>

    @PUT("${ApiConstants.API_VERSION}/institutions/{id}/options")
    fun updateInstituteOptions(@Path("id") id: String, @Body options: UpdateInstituteOptionsRequest): Completable
}