package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 15/01/2018.
 */
class UserModelRemote(
        @SerializedName("id") var id: String ?= "",
        @SerializedName("full_name") var fullName: String ?= "",
        @SerializedName("text_display") var textDisplay: String ?= "",
        @SerializedName("avatar") var avatar: String ?= "",
        @SerializedName("is_contact") var isContact: String ?= "",
        @SerializedName("type") var type: String ?= "",
        @SerializedName("role") var role: RoleRemote?= RoleRemote(),
        @SerializedName("class") var classesList: List<ClassRemote> ?= listOf(),
        @SerializedName("children") var children: List<ChildModelRemote> ?= listOf()
) {
    companion object {
        const val CONTACT = "contact"
        const val STAFF = "staff"
    }

}