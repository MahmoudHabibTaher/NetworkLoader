package com.parent.domain.classes

import com.google.gson.annotations.SerializedName
import com.parent.entities.ClassRemote

/**
 * Created by ahmedmahmoud on 2/21/18.
 */
class DashboardClassesListDataResponce
        (@SerializedName("total_classes") val totalClasses: Int? = 0, @SerializedName("total_children") val totalChildren: Int? =0,
                               @SerializedName("classes") val classes: List<ClassRemote>? = listOf())

