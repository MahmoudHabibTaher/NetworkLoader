package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 18/12/2017.
 */
class InstituteStaffModelRemote(
        @SerializedName("id")  var id: String = "",
        @SerializedName("full_name") var fullName: String = "",
        @SerializedName("photo") var photo: String = "",
        @SerializedName("email") var email: String = ""
) 