package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
class ChildPickupRequest(
        @SerializedName("user_id") var userId: String? = "",
        @SerializedName("name") var name: String? = "",
        @SerializedName("pick_up_time") var pickupTime: String? = ""
) {


}