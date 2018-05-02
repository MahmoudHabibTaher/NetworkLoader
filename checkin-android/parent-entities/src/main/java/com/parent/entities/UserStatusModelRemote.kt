package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 15/01/2018.
 */
class UserStatusModelRemote(
        @SerializedName("title") var body: String? = "",
        @SerializedName("type") var type: String? = "",
        @SerializedName("date_time") var dateTime: String? = "",
        @SerializedName("date_time_text") var dateTimeText: String? = "",
        @SerializedName("child") var child: ChildModelRemote? = ChildModelRemote()


) {}