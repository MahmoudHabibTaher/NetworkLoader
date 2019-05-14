package com.bigo.networkloader.demo.pins.list.data.remote.models

import com.google.gson.annotations.SerializedName

class UserRemote(
    @SerializedName("id") val id: String?,
    @SerializedName("username") val userName: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("profile_image") val image: ProfileImageRemote?
)