package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 21/03/2018.
 */
class ServerDateModelRemote(
        @SerializedName("datetime") var datetime: String? = "",
        @SerializedName("timezone") var timezone: String? = ""
)