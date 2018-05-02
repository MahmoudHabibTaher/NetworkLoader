package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 9/14/17.
 */
class RefreshTokenResponse(@SerializedName("token") var refreshToken: String)