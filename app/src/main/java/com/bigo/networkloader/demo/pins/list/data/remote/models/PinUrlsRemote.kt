package com.bigo.networkloader.demo.pins.list.data.remote.models

import com.google.gson.annotations.SerializedName

class PinUrlsRemote(
    @SerializedName("raw") val raw: String?,
    @SerializedName("full") val full: String?,
    @SerializedName("regular") val regular: String?,
    @SerializedName("small") val small: String?,
    @SerializedName("thumb") val thumb: String?
)