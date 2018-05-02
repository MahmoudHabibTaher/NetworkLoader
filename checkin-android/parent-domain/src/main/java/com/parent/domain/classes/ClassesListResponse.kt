package com.parent.domain.classes

import com.google.gson.annotations.SerializedName

/**
 * Created by habib on 2/11/18.
 */
class ClassesListResponse(@SerializedName("current_page") val currentPage:Int? = 1,
                          @SerializedName("data") val data: ClassesListData?)