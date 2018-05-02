package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 9/8/17.
 */
data class ForgetPassRequest(@SerializedName("email") var email: String? = "")