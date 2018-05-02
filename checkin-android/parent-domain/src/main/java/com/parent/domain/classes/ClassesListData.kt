package com.parent.domain.classes

import com.google.gson.annotations.SerializedName
import com.parent.entities.ClassRemote

/**
 * Created by habib on 2/11/18.
 */
class ClassesListData(@SerializedName("total_classes") val totalClasses: Int? = 0,
                      @SerializedName("classes") val classes: List<ClassRemote>? = listOf())

