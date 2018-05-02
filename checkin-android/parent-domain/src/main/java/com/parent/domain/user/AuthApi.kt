package com.parent.domain.user

import com.parent.domain.common.network.ApiConstants
import com.parent.entities.*
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by mahmoud on 9/8/17.
 */
interface AuthApi {
    @POST("${ApiConstants.API_VERSION}/login")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>

    @GET("${ApiConstants.API_VERSION}/user/view")
    fun getUser(): Single<BaseResponse<UserInfoResponse>>

    @POST("${ApiConstants.API_VERSION}/tokens/create")
    fun saveOneSignalToken(@Body token: SavePushNotificationRequest): Completable

    @GET("${ApiConstants.API_VERSION}/user/token/refresh")
    fun refreshAccessToken(): Single<LoginResponse>

    @GET("${ApiConstants.API_VERSION}/password/token/validate/{confirmation_code}")
    fun checkResetLink(@Path("confirmation_code") link: String): Completable

    @GET("${ApiConstants.API_VERSION}/user/token/validate/{confirmation_code}")
    fun checkActivationLinkExpired(@Path("confirmation_code") link: String): Completable

    @PUT("${ApiConstants.API_VERSION}/user/verify")
    fun createPassword(@Body createPassword: CreatePasswordRequest): Single<LoginResponse>

    @PUT("${ApiConstants.API_VERSION}/password/reset")
    fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): Single<LoginResponse>

    @POST("${ApiConstants.API_VERSION}/password/token")
    fun forgetPassword(@Body forgetPassRequest: ForgetPassRequest): Completable

    @POST("${ApiConstants.API_VERSION}/password/token")
    fun requestNewResetPasswordLink(@Body requestResetPasswordLinkRequest: RequestResetPasswordLinkRequest
    ): Completable

    @GET("${ApiConstants.API_VERSION}/user/token/resend/{email}")
    fun requestNewActivationLink(@Path("email") email: String): Completable

}