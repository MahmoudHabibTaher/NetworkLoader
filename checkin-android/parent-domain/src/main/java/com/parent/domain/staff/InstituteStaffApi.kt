package com.parent.domain.staff

import com.parent.entities.BaseResponse
import com.parent.entities.InstituteStaffModelRemote
import com.parent.domain.common.network.ApiConstants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by mahmoud on 11/28/17.
 */
interface InstituteStaffApi {
    @GET("${ApiConstants.API_VERSION}/institutions/{institution_id}/staff")
    fun getInstituteStaff(@Path("institution_id") institutionId: String): Single<BaseResponse<List<InstituteStaffModelRemote>>>
}