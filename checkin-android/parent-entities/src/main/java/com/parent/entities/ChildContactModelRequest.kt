package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
class ChildContactModelRequest(

        @SerializedName("institution_id") var institutionId: String? = null,
        @SerializedName("full_name") var fullName: String? = null,
        @SerializedName("avatar") var photo: String? = null,
        @SerializedName("relation_id") var relation: String? = null,
        @SerializedName("contact_role_id") var role: String? = null,
        @SerializedName("is_parent_login") var isLogin: String? = null,
        @SerializedName("email") var email: String? = null,
        @SerializedName("phone") var phoneNumber: String? = null,
        @SerializedName("hide_phone_number") var hidePhoneNumber: String? = null,
        @SerializedName("protected_address") var protectedAddress: String? = null,
        @SerializedName("street") var street: String? = null,
        @SerializedName("zip_code") var zipCode: String? = null,
        @SerializedName("country_id") var countryId: String? = "",
        @SerializedName("city_name") var cityName: String? = "",
        @SerializedName("mobile") var mobileNumber: String? = null,
        @SerializedName("company_id") var companyId: String? = null


) {

}