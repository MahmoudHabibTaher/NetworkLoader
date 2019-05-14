package com.bigo.networkloader.demo.pins.list.data.remote.models

import com.google.gson.annotations.SerializedName

class PinRemote(
    @SerializedName("id") val id: String?,
    @SerializedName("user") val user: UserRemote?,
    @SerializedName("urls") val urls: PinUrlsRemote?
)