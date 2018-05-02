package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by raede on 24/11/2017.
 */
open class AttendingChildrenModelRealm(
        var childId: String = "",
        var childName: String = "",
        var isAttending: Boolean = true,
        var type: String = "",
        var attending_note: String = ""
):RealmObject() {}