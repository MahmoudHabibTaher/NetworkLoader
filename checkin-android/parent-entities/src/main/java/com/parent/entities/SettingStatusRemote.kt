package com.parent.entities

import com.google.gson.annotations.SerializedName

data class SettingStatusRemote(@SerializedName("name") val name: String? = "",
                               @SerializedName("key") val key: String? = "",
                               @SerializedName("related_key") val relatedKey: String? = "",
                               @SerializedName("value") val value: Boolean? = false,
                               @SerializedName("hint") val hint: String? = "")