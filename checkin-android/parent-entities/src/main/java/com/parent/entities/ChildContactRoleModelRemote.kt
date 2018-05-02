package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 03/01/2018.
 */
class ChildContactRoleModelRemote(
        @SerializedName("id") var id: String? = "",
        @SerializedName("name") var name: String? = "",
        @SerializedName("description") var description: String? = ""
        )