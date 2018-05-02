package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Raed Ezzat on 04/01/2018.
 */
class ChildContactPageRemote(

        @SerializedName("current_page") var currentPage: String? = "",
        @SerializedName("data") var data: List<ChildContactModelResponse>? = emptyList(),
        @SerializedName("first_page_url") var firstPageUrl: String? = "",
        @SerializedName("from") var from: String? = "",
        @SerializedName("last_page") var lastPage: String? = "",
        @SerializedName("last_page_url") var lastPageUrl: String? = "",
        @SerializedName("next_page_url") var nextPageUrl: String? = "",
        @SerializedName("path") var path: String? = "",
        @SerializedName("per_page") var perPage: String? = "",
        @SerializedName("prev_page_url") var prevPageUrl: String? = "",
        @SerializedName("to") var to: String? = "",
        @SerializedName("total") var total: String? = ""
) {

}