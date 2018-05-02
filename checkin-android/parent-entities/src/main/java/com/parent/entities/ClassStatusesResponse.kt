package com.parent.entities

import com.google.gson.annotations.SerializedName

data class ClassStatusesResponse(@SerializedName("statuses") val statuses: List<StatusRemote>? =
                                 listOf())