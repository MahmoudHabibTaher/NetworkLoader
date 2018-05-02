package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 11/22/17.
 */
data class ValidationErrorRemote(@SerializedName("field") val field: String = "",
                                 @SerializedName("errorCode") val errorCode: String = "",
                                 @SerializedName("errorMsg") val errorMsg: String = "")