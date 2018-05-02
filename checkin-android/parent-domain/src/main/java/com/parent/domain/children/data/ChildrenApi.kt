package com.parent.domain.children.data

import android.provider.ContactsContract
import com.parent.domain.common.network.ApiConstants
import com.parent.entities.*
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by mahmoud on 12/8/17.
 */
interface ChildrenApi {
    @GET("${ApiConstants.API_VERSION}/children/{class_id}/class")
    fun getClassChildren(@Path("class_id") classId: String): Single<BaseResponse<ClassChildrenResponse>>

    @GET("${ApiConstants.API_VERSION}/children/{child_id}")
    fun getChildDetails(@Path("child_id") childId: String, @Query("institution_id") institutionId: String): Single<ChildModelResponse>

    @PUT("${ApiConstants.API_VERSION}/children/{child_id}")
    fun editChildDetails(@Path("child_id") childId: String, @Body child: ChildModelRequest): Completable

    @POST("${ApiConstants.API_VERSION}/children/vacation/report")
    fun reportChildVacation(@Body childLeaveRequestModelRemote: ChildLeaveRequestModelRemote): Completable

    @POST("${ApiConstants.API_VERSION}/children/vacation/report")
    fun reportChildSick(@Body childLeaveRequestModelRemote: ChildLeaveRequestModelRemote): Completable

    @GET("${ApiConstants.API_VERSION}/children/healthinfo/{child_id}")
    fun getChildHealthDetails(@Path("child_id") childId: String, @Query("institution_id") institutionId: String): Single<ChildHealthModelResponse>

    @GET("${ApiConstants.API_VERSION}/children/pickup/{child_id}")
    fun getChildPickup(@Path("child_id") childId: String): Single<BaseResponse<ChildPickupModelResponse>>

    @POST("${ApiConstants.API_VERSION}/children/pickup/{child_id}")
    fun editChildPickup(@Path("child_id") childId: String, @Body body: ChildPickupRequest): Completable

    @PUT("${ApiConstants.API_VERSION}/children/healthinfo/{child_id}")
    fun editChildHealthDetails(@Path("child_id") childId: String, @Body body: ChildHealthModelData): Completable

    @GET("${ApiConstants.API_VERSION}/children/daily_report/{child_id}")
    fun getChildDailyReport(@Path("child_id") childId: String, @Query("day") day: String): Single<BaseResponse<List<ReportItemRemote>>>

    @GET("${ApiConstants.API_VERSION}/children/{child_id}/permits")
    fun getChildPermissions(@Path("child_id") childId: String,
                            @Query("institution_id") institutionId: String): Single<BaseResponse<List<ChildPermissionRemote>>>

    @POST("${ApiConstants.API_VERSION}/children/{child_id}/permits")
    fun addChildPermission(@Path("child_id") childId: String,
                           @Body request: AddChildPermissionRemoteRequest): Single<BaseResponse<Any>>

    @POST("${ApiConstants.API_VERSION}/children/{child_id}/permits/{permission_id}/reply")
    fun saveChildPermissionReply(@Path("child_id") childId: String,
                                 @Path("permission_id") permissionId: String,
                                 @Body replyRequest: PermissionReplyRequest): Completable

    @GET("${ApiConstants.API_VERSION}/children/{child_id}/leaves")
    fun getChildLeaves(@Path("child_id") childId: String): Single<ChildLeavesRemote>

    @POST("${ApiConstants.API_VERSION}/children/checkin/{child_id}")
    fun checkInChild(@Path("child_id") childId: String): Completable

    @POST("${ApiConstants.API_VERSION}/children/checkout/{child_id}")
    fun checkOutChild(@Path("child_id") childId: String): Completable


    @GET("${ApiConstants.API_VERSION}/children/{child_id}/gallery")
    fun getChildGallery(@Path("child_id") childId: String,@Query("type")type:String="",@Query("page") pageNumber:Int=1): Single<BaseResponse<ChildGalleryResponse>>



}