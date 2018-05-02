package com.parent.domain.realm.entities

import io.realm.RealmObject

/**
 * Created by raede on 24/11/2017.
 */
open class AttendingRealm(
        var id: String = "",
        var isAttending: Boolean = false,
        var name: String = "",
        var image: String = "",
        var dayId: String = ""
) : RealmObject() {}