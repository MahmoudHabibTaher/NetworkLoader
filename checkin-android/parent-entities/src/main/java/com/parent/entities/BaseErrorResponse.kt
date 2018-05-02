package com.parent.entities

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 10/5/17.
 */
open class BaseErrorResponse<T>(@SerializedName("Status") var status: String? = "",
                                @SerializedName("Message") var message: String? = "",
                                @SerializedName("Errors") var data: T? = null) {
    override fun toString(): String = Gson().toJson(this)

    companion object
}