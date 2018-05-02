package com.parent.domain.classes

import com.parent.domain.common.network.ApiConstants
import com.parent.entities.BaseResponse
import com.parent.entities.ClassDetailsRemote
import com.parent.entities.ClassRemote
import com.parent.entities.ClassStatusesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by mahmoud on 12/6/17.
 */
interface ClassesApi {
    @GET("${ApiConstants.API_VERSION}/classes/{class_id}/details")
    fun getClassDetails(@Path("class_id") classId: String): Single<BaseResponse<ClassDetailsRemote>>

    @GET("${ApiConstants.API_VERSION}/classes/{institute_id}/institution")
    fun getClasses(@Path("institute_id") instituteId: String): Single<BaseResponse<List<ClassRemote>>>

    @GET("${ApiConstants.API_VERSION}/classes/{institute_id}/institution/dashboard")
    fun getDashboardClasses(@Path("institute_id") instituteId: String): Single<BaseResponse<DashboardClassesListDataResponce>>

    @GET("${ApiConstants.API_VERSION}/classes/{institute_id}/users/{user_id}")
    fun getUserClasses(@Path("institute_id") instituteId: String,
                       @Path("user_id") userId: String): Single<BaseResponse<List<ClassRemote>>>

    @GET("${ApiConstants.API_VERSION}/classes/{class_id}/statuses")
    fun getClassStatuses(@Path("class_id") classId: String): Single<BaseResponse<ClassStatusesResponse>>

    @GET("${ApiConstants.API_VERSION}/classes/{class_id}/statuses/{status_type}")
    fun getClassStatusDetails(@Path("class_id") classId: String,
                              @Path("status_type") statusType: String): Single<BaseResponse<ClassDetailsRemote>>

    @GET("${ApiConstants.API_VERSION}/classes/{class_id}")
    fun getClassSettings(@Path("class_id") classId: String): Single<BaseResponse<ClassRemote>>
}