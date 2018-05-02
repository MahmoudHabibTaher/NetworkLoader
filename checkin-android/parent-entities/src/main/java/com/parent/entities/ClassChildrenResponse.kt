package com.parent.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by mahmoud on 12/8/17.
 */
data class ClassChildrenResponse(@SerializedName("children") val children: List<ClassChildRemote>?)