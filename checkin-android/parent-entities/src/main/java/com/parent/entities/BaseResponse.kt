package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 9/14/17.
 */
open class BaseResponse<T>(@SerializedName("Status") var status: String? = "",
                           @SerializedName("Message") var message: String? = "",
                           @SerializedName("Data") var data: T? = null) {
    companion object {
        fun <T> buildSuccessResponse(status: String? = "Success", message: String? = "Success",
                                     data: T) =
                BaseResponse(status, message, data)

        fun <T> buildSuccessEmptyDataResponse(status: String? = "Success", message: String? = "Success") =
                BaseResponse<T>(status, message, null)
    }
}