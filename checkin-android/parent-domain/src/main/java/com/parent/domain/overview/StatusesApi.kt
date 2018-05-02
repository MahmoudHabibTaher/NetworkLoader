package com.parent.domain.overview

import com.parent.domain.classes.ClassesListResponse
import com.parent.domain.common.network.ApiConstants
import com.parent.entities.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by mahmoud on 12/6/17.
 */
interface StatusesApi {
    @GET("${ApiConstants.API_VERSION}/institutions/{institution_id}/statuses")
    fun getInstituteStatus(@Path("institution_id") instituteId: String): Single<BaseResponse<Statuses>>
    @GET("${ApiConstants.API_VERSION}/institutions/{institution_id}/statuses/{status_code}")
    fun getInstituteStatusData(@Path("institution_id") instituteId: String,@Path("status_code") statusCode:String): Single<BaseResponse<List<StatusDataRemote>>>
}