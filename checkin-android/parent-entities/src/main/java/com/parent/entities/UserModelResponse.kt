package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 10/3/17.
 */
data class UserModelResponse(@SerializedName("id") var id: String? = "",
                             @SerializedName("full_name") var fullName: String? = "",
                             @SerializedName("email") var email: String? = "",
                             @SerializedName("institution_id") var institutionId: String? = "",
                             @SerializedName("avatar") var avatar: String? = "",
                             @SerializedName("city_name") var cityName: String? = "",
                             @SerializedName("city") var city: CityRemote? = null,
                             @SerializedName("country_id") var countryId: String? = "",
                             @SerializedName("country") var country: CountryRemote? = null,
                             @SerializedName("mobile") var mobile: String? = "",
                             @SerializedName("work_number") var workNumber: String? = "",
                             @SerializedName("phone") var phone: String? = "",
                             @SerializedName("street") var street: String? = "",
                             @SerializedName("zip_code") var zipCode: String? = ""

)