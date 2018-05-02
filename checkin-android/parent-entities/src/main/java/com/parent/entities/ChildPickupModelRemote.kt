package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
class ChildPickupModelResponse(
        @SerializedName("pickup") var pickup: ChildPickupModelRemote? = ChildPickupModelRemote()
)

class ChildPickupModelRemote(
        @SerializedName("id") var id: String? = "",
        @SerializedName("child_id") var childId: String? = "",
        @SerializedName("user_id") var userId: String? = "",
        @SerializedName("name") var name: String? = "",
        @SerializedName("created_at") var createdAt: String? = "",
        @SerializedName("updated_at") var updatedAt: String? = "",
        @SerializedName("deleted_at") var deletedAt: String? = "",
        @SerializedName("is_person") var isPerson: String? = "",
        @SerializedName("pickup_date_time") var pickupDateTime: String? = "",
        @SerializedName("pick_up_time") var pickupTime: String? = "",
        @SerializedName("person") var person: ChildContactModelResponse? = ChildContactModelResponse()
) {

}