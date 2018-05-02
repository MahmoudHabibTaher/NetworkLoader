package com.parent.domain.status

import com.parent.domain.common.network.ApiConstants
import com.parent.entities.*
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by mahmoud on 9/19/17.
 */
interface StatusesApi {
    @GET("${ApiConstants.API_VERSION}/server/time")
    fun getServerTime(): Single<BaseResponse<ServerDateModelRemote>>

    @POST("${ApiConstants.API_VERSION}/statuses/sleep/wakeup")
    fun reportChildrenSleepWakeup(@Body sleep: ReportChildSleepWakeUpRequestModelRemote): Single<BaseResponse<ExcludedChildrenRemote>>

    @GET("${ApiConstants.API_VERSION}/institutions/{institution_id}/opening")
    fun getInstitutionOpeningHours(@Path("institution_id") institutionId: String): Single<BaseResponse<List<OpeningHourDayModelRemote>>>

    @POST("${ApiConstants.API_VERSION}/statuses/toilet/diaper")
    fun reportChildrenDiaperToilet(@Body request: List<ReportChildToiletDiaperItemModelRemote>): Single<BaseResponse<ExcludedChildrenRemote>>

    @POST("${ApiConstants.API_VERSION}/statuses/mood")
    fun reportChildrenMood(@Body request: List<ReportChildMoodItemModelRemote>): Single<BaseResponse<ExcludedChildrenRemote>>

    @POST("${ApiConstants.API_VERSION}/statuses/meal")
    fun reportChildrenMeal(@Body request: List<ReportChildMealItemModelRemote>): Single<BaseResponse<ExcludedChildrenRemote>>


    @POST("${ApiConstants.API_VERSION}/statuses/checkin")
    fun checkIn(@Body request: CheckInOutRemoteRequest): Single<BaseResponse<Any>>

    @POST("${ApiConstants.API_VERSION}/statuses/checkout")
    fun checkOut(@Body request: CheckInOutRemoteRequest): Single<BaseResponse<Any>>

    @POST("${ApiConstants.API_VERSION}/statuses/vacation")
    fun reportLeave(@Body request: ReportLeaveRemoteRequest): Single<BaseResponse<Any>>

    @POST("${ApiConstants.API_VERSION}/statuses/trip")
    fun reportTrip(@Body request: ReportTripRemoteRequest): Single<BaseResponse<Any>>

    @POST("${ApiConstants.API_VERSION}/statuses/bus")
    fun reportBus(@Body request: ReportBusRemoteRequest): Single<BaseResponse<Any>>

    @POST("${ApiConstants.API_VERSION}/statuses/meeting")
    fun reportMeeting(@Body request: ReportMeetingRemoteRequest): Single<BaseResponse<Any>>

    @GET("${ApiConstants.API_VERSION}/institutions/{institution_id}/meals")
    fun getInstituteMeal(@Path("institution_id") institutionId: String): Single<BaseResponse<List<InstituteMealRemote>>>

}