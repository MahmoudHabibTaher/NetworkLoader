package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 03/01/2018.
 */
class AddressModelRemote(
        @SerializedName("street") var street: String? = "",
        @SerializedName("zip_code") var zipCode: String? = "",
        @SerializedName("country") var country: CountryRemote? = CountryRemote(),
        @SerializedName("city") var city: CityRemote? = CityRemote()
        )