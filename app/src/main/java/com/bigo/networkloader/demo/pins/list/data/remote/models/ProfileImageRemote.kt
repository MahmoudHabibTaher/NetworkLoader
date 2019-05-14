package com.bigo.networkloader.demo.pins.list.data.remote.models

import com.google.gson.annotations.SerializedName

class ProfileImageRemote(
    @SerializedName("small") val small: String?,
    @SerializedName("medium") val medium: String?,
    @SerializedName("large") val large: String?
)