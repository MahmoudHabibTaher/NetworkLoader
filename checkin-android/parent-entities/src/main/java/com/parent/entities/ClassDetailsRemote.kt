package com.parent.entities

import com.google.gson.annotations.SerializedName

data class ClassDetailsRemote(@SerializedName("class") val clazz: ClassRemote? = null,
                              @SerializedName("staff") val staff: StaffList? = null,
                              @SerializedName("children") val children: ClassChildren? = null) {

    data class ClassChildren(@SerializedName("current") val current: ChildrenList? = ChildrenList(),
                             @SerializedName("future") val future: ChildrenList? = ChildrenList())

    data class ChildrenList(@SerializedName("total") val total: Int? = 0,
                            @SerializedName("children") val children: List<ClassChildRemote>? = listOf())

    data class StaffList(@SerializedName("total") val total: Int? = 0,
                         @SerializedName("staff") val staff: List<ClassStaffRemote>? = listOf())
}