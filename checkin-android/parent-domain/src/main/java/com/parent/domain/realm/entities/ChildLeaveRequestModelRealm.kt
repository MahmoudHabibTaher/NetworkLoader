package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by Raed Ezzat on 31/12/2017.
 */
open class ChildLeaveRequestModelRealm(
        var startDate: String = "",
        var endDate: String = "",
        var note: String = "",
        var childId: String = "",
        var institutionId: String = "",
        var type: String = ""
) : RealmObject()