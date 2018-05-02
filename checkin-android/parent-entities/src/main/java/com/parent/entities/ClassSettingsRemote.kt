package com.parent.entities

import com.google.gson.annotations.SerializedName

data class ClassSettingsRemote(@SerializedName("status") val statuses: List<SettingStatusRemote>? = listOf())