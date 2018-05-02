package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 03/01/2018.
 */
class ChildContactProfile(
        @SerializedName("profile") var profile: ChildContactModelResponse = ChildContactModelResponse()
)