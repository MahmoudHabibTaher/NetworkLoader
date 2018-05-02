package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 14/07/2017.
 */

class UsersListPageResponse(status: String? = "", message: String? = "",
                            data: UsersListPage? = UsersListPage()) : BaseResponse<UsersListPage>(status, message, data)

data class UsersListPage(
        @SerializedName("data") var data: List<UserModelRemote>? = listOf(),
        @SerializedName("current_page") var currentPage: String? = "",
        @SerializedName("first_page_url") var firstPageUrl: String? = "",
        @SerializedName("from") var from: String? = "",
        @SerializedName("last_page") var lastPage: String? = "",
        @SerializedName("path") var path: String? = "",
        @SerializedName("per_page") var perPage: String? = "",
        @SerializedName("to") var to: String? = "",
        @SerializedName("total") var total: String? = ""
)