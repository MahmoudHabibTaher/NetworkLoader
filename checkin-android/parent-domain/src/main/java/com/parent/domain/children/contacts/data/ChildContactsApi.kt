package com.parent.domain.children.contacts.data

import com.parent.domain.common.network.ApiConstants
import com.parent.entities.*
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by mahmoud on 12/8/17.
 */
interface ChildContactsApi {

    @GET("${ApiConstants.API_VERSION}/children/{child_id}/contacts")
    fun getChildContacts(@Path("child_id") childId: String, @Query("page") page: Int): Single<BaseResponse<ChildContactPageRemote>>

    @GET("${ApiConstants.API_VERSION}/children/{child_id}/role/{contact_role_id}")
    fun getChildContactsWithParentRole(@Path("child_id") childId: String,
                                       @Path("contact_role_id") contactRoleId: String): Single<BaseResponse<List<ChildContactModelResponse>>>

    @GET("${ApiConstants.API_VERSION}/children/contacts/{contact_id}/permission/pickup")
    fun getChildPickUpContacts(@Path("child_id") childId: String): Single<BaseResponse<List<ChildContactModelResponse>>>

    @GET("${ApiConstants.API_VERSION}/children/{child_id}/contacts/{contact_id}")
    fun getChildContactProfileDetails(@Path("child_id") childId: String,
                                      @Path("contact_id") contactId: String): Single<BaseResponse<ChildContactModelResponse>>

    @PUT("${ApiConstants.API_VERSION}/children/{child_id}/contacts/{contact_id}")
    fun editChildContactProfile(@Path("child_id") childId: String, @Path("contact_id") contact_id: String,
                                @Body contactModel: ChildContactModelRequest): Single<BaseResponse<Int>>

    @POST("${ApiConstants.API_VERSION}/children/{child_id}/contacts")
    fun addNewChildContactProfile(@Path("child_id") childId: String, @Body contactModel: AddNewChildContactRequest):
            Single<BaseResponse<ChildContactModelResponse>>

    @GET("${ApiConstants.API_VERSION}/contacts/roles")
    fun getChildContactsRoles(): Single<BaseResponse<List<ChildContactRoleModelRemote>>>

    @GET("${ApiConstants.API_VERSION}/contacts/relations")
    fun getChildContactsRelations(): Single<BaseResponse<List<ChildContactRelationModelRemote>>>

    @DELETE("${ApiConstants.API_VERSION}/children/{child_id}/contacts/{contact_id}")
    fun removeContactFromChild(@Path("child_id") childId: String, @Path("contact_id") contactId: String): Single<BaseResponse<List<String>>>
}