package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
class ChildContactModelResponse(
        @SerializedName("id") var id: String? = null,

        @SerializedName("full_name") var fullName: String? = null,

        @SerializedName("avatar") var photo: String? = null,
        @SerializedName("image") var image: String? = null,

        @SerializedName("is_login") var isLogin: String? = null,
        @SerializedName("parent_login") var hasLogin: String? = null,

        @SerializedName("relation") var relation: ChildContactRelationModelRemote? = null,

        @SerializedName("contact_role") var role: ChildContactRoleModelRemote? = null,


        @SerializedName("child_id") var childId: String? = null,

        @SerializedName("phone") var phoneNumber: String? = null,
        @SerializedName("email") var email: String? = null,
        @SerializedName("mobile") var mobileNumber: String? = null,
        @SerializedName("hide_phone_number") var hidePhoneNumber: String? = null,
        @SerializedName("protected_address") var protectedAddress: String? = null,
        @SerializedName("street") var street: String? = null,
        @SerializedName("zip_code") var zipCode: String? = null,
        @SerializedName("country") var country: CountryRemote? = CountryRemote(),
        @SerializedName("city") var city: CityRemote? = CityRemote(),

        @SerializedName("related_children") var relatedChildren: String? = ""
) {

}