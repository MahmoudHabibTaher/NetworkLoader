package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 9/14/17.
 */
data class LoginRequest(@SerializedName("email") var email: String,
                        @SerializedName("password") var password: String)