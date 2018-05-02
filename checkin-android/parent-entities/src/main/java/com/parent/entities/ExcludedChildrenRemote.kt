package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by raede on 03/12/2017.
 */
class ExcludedChildrenRemote(
        @SerializedName("excluded_children") var excludedChildren: List<String>? = null)