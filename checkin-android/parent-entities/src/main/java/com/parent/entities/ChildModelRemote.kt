package com.parent.entities

import com.google.gson.annotations.SerializedName
import com.parent.entities.ClassRemote

/**
 * Created by Raed Ezzat on 15/01/2018.
 */
class ChildModelRemote(
        @SerializedName("id") var id: String? = "",
        @SerializedName("full_name") var name: String ?= "",
        @SerializedName("avatar") var avatar: String ?= "",
        @SerializedName("class") var classModel: ClassRemote?= ClassRemote()

) {
}